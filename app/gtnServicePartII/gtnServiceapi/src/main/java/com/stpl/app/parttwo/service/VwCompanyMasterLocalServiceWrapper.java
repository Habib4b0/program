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
 * Provides a wrapper for {@link VwCompanyMasterLocalService}.
 *
 * @author
 * @see VwCompanyMasterLocalService
 * @generated
 */
@ProviderType
public class VwCompanyMasterLocalServiceWrapper
	implements VwCompanyMasterLocalService,
		ServiceWrapper<VwCompanyMasterLocalService> {
	public VwCompanyMasterLocalServiceWrapper(
		VwCompanyMasterLocalService vwCompanyMasterLocalService) {
		_vwCompanyMasterLocalService = vwCompanyMasterLocalService;
	}

	/**
	* Adds the vw company master to the database. Also notifies the appropriate model listeners.
	*
	* @param vwCompanyMaster the vw company master
	* @return the vw company master that was added
	*/
	@Override
	public com.stpl.app.parttwo.model.VwCompanyMaster addVwCompanyMaster(
		com.stpl.app.parttwo.model.VwCompanyMaster vwCompanyMaster) {
		return _vwCompanyMasterLocalService.addVwCompanyMaster(vwCompanyMaster);
	}

	/**
	* Creates a new vw company master with the primary key. Does not add the vw company master to the database.
	*
	* @param companyMasterSid the primary key for the new vw company master
	* @return the new vw company master
	*/
	@Override
	public com.stpl.app.parttwo.model.VwCompanyMaster createVwCompanyMaster(
		int companyMasterSid) {
		return _vwCompanyMasterLocalService.createVwCompanyMaster(companyMasterSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _vwCompanyMasterLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the vw company master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param companyMasterSid the primary key of the vw company master
	* @return the vw company master that was removed
	* @throws PortalException if a vw company master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.VwCompanyMaster deleteVwCompanyMaster(
		int companyMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _vwCompanyMasterLocalService.deleteVwCompanyMaster(companyMasterSid);
	}

	/**
	* Deletes the vw company master from the database. Also notifies the appropriate model listeners.
	*
	* @param vwCompanyMaster the vw company master
	* @return the vw company master that was removed
	*/
	@Override
	public com.stpl.app.parttwo.model.VwCompanyMaster deleteVwCompanyMaster(
		com.stpl.app.parttwo.model.VwCompanyMaster vwCompanyMaster) {
		return _vwCompanyMasterLocalService.deleteVwCompanyMaster(vwCompanyMaster);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _vwCompanyMasterLocalService.dynamicQuery();
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
		return _vwCompanyMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _vwCompanyMasterLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _vwCompanyMasterLocalService.dynamicQuery(dynamicQuery, start,
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
		return _vwCompanyMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _vwCompanyMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.parttwo.model.VwCompanyMaster fetchVwCompanyMaster(
		int companyMasterSid) {
		return _vwCompanyMasterLocalService.fetchVwCompanyMaster(companyMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _vwCompanyMasterLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _vwCompanyMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _vwCompanyMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _vwCompanyMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the vw company master with the primary key.
	*
	* @param companyMasterSid the primary key of the vw company master
	* @return the vw company master
	* @throws PortalException if a vw company master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.VwCompanyMaster getVwCompanyMaster(
		int companyMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _vwCompanyMasterLocalService.getVwCompanyMaster(companyMasterSid);
	}

	/**
	* Returns a range of all the vw company masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw company masters
	* @param end the upper bound of the range of vw company masters (not inclusive)
	* @return the range of vw company masters
	*/
	@Override
	public java.util.List<com.stpl.app.parttwo.model.VwCompanyMaster> getVwCompanyMasters(
		int start, int end) {
		return _vwCompanyMasterLocalService.getVwCompanyMasters(start, end);
	}

	/**
	* Returns the number of vw company masters.
	*
	* @return the number of vw company masters
	*/
	@Override
	public int getVwCompanyMastersCount() {
		return _vwCompanyMasterLocalService.getVwCompanyMastersCount();
	}

	/**
	* Updates the vw company master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param vwCompanyMaster the vw company master
	* @return the vw company master that was updated
	*/
	@Override
	public com.stpl.app.parttwo.model.VwCompanyMaster updateVwCompanyMaster(
		com.stpl.app.parttwo.model.VwCompanyMaster vwCompanyMaster) {
		return _vwCompanyMasterLocalService.updateVwCompanyMaster(vwCompanyMaster);
	}

	@Override
	public VwCompanyMasterLocalService getWrappedService() {
		return _vwCompanyMasterLocalService;
	}

	@Override
	public void setWrappedService(
		VwCompanyMasterLocalService vwCompanyMasterLocalService) {
		_vwCompanyMasterLocalService = vwCompanyMasterLocalService;
	}

	private VwCompanyMasterLocalService _vwCompanyMasterLocalService;
}