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
 * Provides a wrapper for {@link CffMasterLocalService}.
 *
 * @author
 * @see CffMasterLocalService
 * @generated
 */
@ProviderType
public class CffMasterLocalServiceWrapper implements CffMasterLocalService,
	ServiceWrapper<CffMasterLocalService> {
	public CffMasterLocalServiceWrapper(
		CffMasterLocalService cffMasterLocalService) {
		_cffMasterLocalService = cffMasterLocalService;
	}

	/**
	* Adds the cff master to the database. Also notifies the appropriate model listeners.
	*
	* @param cffMaster the cff master
	* @return the cff master that was added
	*/
	@Override
	public com.stpl.app.parttwo.model.CffMaster addCffMaster(
		com.stpl.app.parttwo.model.CffMaster cffMaster) {
		return _cffMasterLocalService.addCffMaster(cffMaster);
	}

	/**
	* Creates a new cff master with the primary key. Does not add the cff master to the database.
	*
	* @param cffMasterSid the primary key for the new cff master
	* @return the new cff master
	*/
	@Override
	public com.stpl.app.parttwo.model.CffMaster createCffMaster(
		int cffMasterSid) {
		return _cffMasterLocalService.createCffMaster(cffMasterSid);
	}

	/**
	* Deletes the cff master from the database. Also notifies the appropriate model listeners.
	*
	* @param cffMaster the cff master
	* @return the cff master that was removed
	*/
	@Override
	public com.stpl.app.parttwo.model.CffMaster deleteCffMaster(
		com.stpl.app.parttwo.model.CffMaster cffMaster) {
		return _cffMasterLocalService.deleteCffMaster(cffMaster);
	}

	/**
	* Deletes the cff master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cffMasterSid the primary key of the cff master
	* @return the cff master that was removed
	* @throws PortalException if a cff master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.CffMaster deleteCffMaster(
		int cffMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cffMasterLocalService.deleteCffMaster(cffMasterSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cffMasterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _cffMasterLocalService.dynamicQuery();
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
		return _cffMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _cffMasterLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _cffMasterLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _cffMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _cffMasterLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.stpl.app.parttwo.model.CffMaster fetchCffMaster(int cffMasterSid) {
		return _cffMasterLocalService.fetchCffMaster(cffMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _cffMasterLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the cff master with the primary key.
	*
	* @param cffMasterSid the primary key of the cff master
	* @return the cff master
	* @throws PortalException if a cff master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.CffMaster getCffMaster(int cffMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cffMasterLocalService.getCffMaster(cffMasterSid);
	}

	/**
	* Returns a range of all the cff masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff masters
	* @param end the upper bound of the range of cff masters (not inclusive)
	* @return the range of cff masters
	*/
	@Override
	public java.util.List<com.stpl.app.parttwo.model.CffMaster> getCffMasters(
		int start, int end) {
		return _cffMasterLocalService.getCffMasters(start, end);
	}

	/**
	* Returns the number of cff masters.
	*
	* @return the number of cff masters
	*/
	@Override
	public int getCffMastersCount() {
		return _cffMasterLocalService.getCffMastersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _cffMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _cffMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cffMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the cff master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param cffMaster the cff master
	* @return the cff master that was updated
	*/
	@Override
	public com.stpl.app.parttwo.model.CffMaster updateCffMaster(
		com.stpl.app.parttwo.model.CffMaster cffMaster) {
		return _cffMasterLocalService.updateCffMaster(cffMaster);
	}

	@Override
	public CffMasterLocalService getWrappedService() {
		return _cffMasterLocalService;
	}

	@Override
	public void setWrappedService(CffMasterLocalService cffMasterLocalService) {
		_cffMasterLocalService = cffMasterLocalService;
	}

	private CffMasterLocalService _cffMasterLocalService;
}