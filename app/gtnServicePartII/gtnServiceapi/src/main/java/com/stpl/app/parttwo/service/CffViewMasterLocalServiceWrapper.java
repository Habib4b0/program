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
 * Provides a wrapper for {@link CffViewMasterLocalService}.
 *
 * @author
 * @see CffViewMasterLocalService
 * @generated
 */
@ProviderType
public class CffViewMasterLocalServiceWrapper
	implements CffViewMasterLocalService,
		ServiceWrapper<CffViewMasterLocalService> {
	public CffViewMasterLocalServiceWrapper(
		CffViewMasterLocalService cffViewMasterLocalService) {
		_cffViewMasterLocalService = cffViewMasterLocalService;
	}

	/**
	* Adds the cff view master to the database. Also notifies the appropriate model listeners.
	*
	* @param cffViewMaster the cff view master
	* @return the cff view master that was added
	*/
	@Override
	public com.stpl.app.parttwo.model.CffViewMaster addCffViewMaster(
		com.stpl.app.parttwo.model.CffViewMaster cffViewMaster) {
		return _cffViewMasterLocalService.addCffViewMaster(cffViewMaster);
	}

	/**
	* Creates a new cff view master with the primary key. Does not add the cff view master to the database.
	*
	* @param cffViewMasterSid the primary key for the new cff view master
	* @return the new cff view master
	*/
	@Override
	public com.stpl.app.parttwo.model.CffViewMaster createCffViewMaster(
		int cffViewMasterSid) {
		return _cffViewMasterLocalService.createCffViewMaster(cffViewMasterSid);
	}

	/**
	* Deletes the cff view master from the database. Also notifies the appropriate model listeners.
	*
	* @param cffViewMaster the cff view master
	* @return the cff view master that was removed
	*/
	@Override
	public com.stpl.app.parttwo.model.CffViewMaster deleteCffViewMaster(
		com.stpl.app.parttwo.model.CffViewMaster cffViewMaster) {
		return _cffViewMasterLocalService.deleteCffViewMaster(cffViewMaster);
	}

	/**
	* Deletes the cff view master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cffViewMasterSid the primary key of the cff view master
	* @return the cff view master that was removed
	* @throws PortalException if a cff view master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.CffViewMaster deleteCffViewMaster(
		int cffViewMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cffViewMasterLocalService.deleteCffViewMaster(cffViewMasterSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cffViewMasterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _cffViewMasterLocalService.dynamicQuery();
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
		return _cffViewMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _cffViewMasterLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _cffViewMasterLocalService.dynamicQuery(dynamicQuery, start,
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
		return _cffViewMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _cffViewMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.parttwo.model.CffViewMaster fetchCffViewMaster(
		int cffViewMasterSid) {
		return _cffViewMasterLocalService.fetchCffViewMaster(cffViewMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _cffViewMasterLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the cff view master with the primary key.
	*
	* @param cffViewMasterSid the primary key of the cff view master
	* @return the cff view master
	* @throws PortalException if a cff view master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.CffViewMaster getCffViewMaster(
		int cffViewMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cffViewMasterLocalService.getCffViewMaster(cffViewMasterSid);
	}

	/**
	* Returns a range of all the cff view masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff view masters
	* @param end the upper bound of the range of cff view masters (not inclusive)
	* @return the range of cff view masters
	*/
	@Override
	public java.util.List<com.stpl.app.parttwo.model.CffViewMaster> getCffViewMasters(
		int start, int end) {
		return _cffViewMasterLocalService.getCffViewMasters(start, end);
	}

	/**
	* Returns the number of cff view masters.
	*
	* @return the number of cff view masters
	*/
	@Override
	public int getCffViewMastersCount() {
		return _cffViewMasterLocalService.getCffViewMastersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _cffViewMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _cffViewMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cffViewMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the cff view master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param cffViewMaster the cff view master
	* @return the cff view master that was updated
	*/
	@Override
	public com.stpl.app.parttwo.model.CffViewMaster updateCffViewMaster(
		com.stpl.app.parttwo.model.CffViewMaster cffViewMaster) {
		return _cffViewMasterLocalService.updateCffViewMaster(cffViewMaster);
	}

	@Override
	public CffViewMasterLocalService getWrappedService() {
		return _cffViewMasterLocalService;
	}

	@Override
	public void setWrappedService(
		CffViewMasterLocalService cffViewMasterLocalService) {
		_cffViewMasterLocalService = cffViewMasterLocalService;
	}

	private CffViewMasterLocalService _cffViewMasterLocalService;
}