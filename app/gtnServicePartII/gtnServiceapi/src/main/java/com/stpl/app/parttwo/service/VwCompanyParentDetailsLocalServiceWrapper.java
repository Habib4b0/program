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
 * Provides a wrapper for {@link VwCompanyParentDetailsLocalService}.
 *
 * @author
 * @see VwCompanyParentDetailsLocalService
 * @generated
 */
@ProviderType
public class VwCompanyParentDetailsLocalServiceWrapper
	implements VwCompanyParentDetailsLocalService,
		ServiceWrapper<VwCompanyParentDetailsLocalService> {
	public VwCompanyParentDetailsLocalServiceWrapper(
		VwCompanyParentDetailsLocalService vwCompanyParentDetailsLocalService) {
		_vwCompanyParentDetailsLocalService = vwCompanyParentDetailsLocalService;
	}

	/**
	* Adds the vw company parent details to the database. Also notifies the appropriate model listeners.
	*
	* @param vwCompanyParentDetails the vw company parent details
	* @return the vw company parent details that was added
	*/
	@Override
	public com.stpl.app.parttwo.model.VwCompanyParentDetails addVwCompanyParentDetails(
		com.stpl.app.parttwo.model.VwCompanyParentDetails vwCompanyParentDetails) {
		return _vwCompanyParentDetailsLocalService.addVwCompanyParentDetails(vwCompanyParentDetails);
	}

	/**
	* Creates a new vw company parent details with the primary key. Does not add the vw company parent details to the database.
	*
	* @param companyParentDetailsSid the primary key for the new vw company parent details
	* @return the new vw company parent details
	*/
	@Override
	public com.stpl.app.parttwo.model.VwCompanyParentDetails createVwCompanyParentDetails(
		int companyParentDetailsSid) {
		return _vwCompanyParentDetailsLocalService.createVwCompanyParentDetails(companyParentDetailsSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _vwCompanyParentDetailsLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the vw company parent details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param companyParentDetailsSid the primary key of the vw company parent details
	* @return the vw company parent details that was removed
	* @throws PortalException if a vw company parent details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.VwCompanyParentDetails deleteVwCompanyParentDetails(
		int companyParentDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _vwCompanyParentDetailsLocalService.deleteVwCompanyParentDetails(companyParentDetailsSid);
	}

	/**
	* Deletes the vw company parent details from the database. Also notifies the appropriate model listeners.
	*
	* @param vwCompanyParentDetails the vw company parent details
	* @return the vw company parent details that was removed
	*/
	@Override
	public com.stpl.app.parttwo.model.VwCompanyParentDetails deleteVwCompanyParentDetails(
		com.stpl.app.parttwo.model.VwCompanyParentDetails vwCompanyParentDetails) {
		return _vwCompanyParentDetailsLocalService.deleteVwCompanyParentDetails(vwCompanyParentDetails);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _vwCompanyParentDetailsLocalService.dynamicQuery();
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
		return _vwCompanyParentDetailsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCompanyParentDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _vwCompanyParentDetailsLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCompanyParentDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _vwCompanyParentDetailsLocalService.dynamicQuery(dynamicQuery,
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
		return _vwCompanyParentDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _vwCompanyParentDetailsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.parttwo.model.VwCompanyParentDetails fetchVwCompanyParentDetails(
		int companyParentDetailsSid) {
		return _vwCompanyParentDetailsLocalService.fetchVwCompanyParentDetails(companyParentDetailsSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _vwCompanyParentDetailsLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _vwCompanyParentDetailsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _vwCompanyParentDetailsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _vwCompanyParentDetailsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the vw company parent details with the primary key.
	*
	* @param companyParentDetailsSid the primary key of the vw company parent details
	* @return the vw company parent details
	* @throws PortalException if a vw company parent details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.VwCompanyParentDetails getVwCompanyParentDetails(
		int companyParentDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _vwCompanyParentDetailsLocalService.getVwCompanyParentDetails(companyParentDetailsSid);
	}

	/**
	* Returns a range of all the vw company parent detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCompanyParentDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw company parent detailses
	* @param end the upper bound of the range of vw company parent detailses (not inclusive)
	* @return the range of vw company parent detailses
	*/
	@Override
	public java.util.List<com.stpl.app.parttwo.model.VwCompanyParentDetails> getVwCompanyParentDetailses(
		int start, int end) {
		return _vwCompanyParentDetailsLocalService.getVwCompanyParentDetailses(start,
			end);
	}

	/**
	* Returns the number of vw company parent detailses.
	*
	* @return the number of vw company parent detailses
	*/
	@Override
	public int getVwCompanyParentDetailsesCount() {
		return _vwCompanyParentDetailsLocalService.getVwCompanyParentDetailsesCount();
	}

	/**
	* Updates the vw company parent details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param vwCompanyParentDetails the vw company parent details
	* @return the vw company parent details that was updated
	*/
	@Override
	public com.stpl.app.parttwo.model.VwCompanyParentDetails updateVwCompanyParentDetails(
		com.stpl.app.parttwo.model.VwCompanyParentDetails vwCompanyParentDetails) {
		return _vwCompanyParentDetailsLocalService.updateVwCompanyParentDetails(vwCompanyParentDetails);
	}

	@Override
	public VwCompanyParentDetailsLocalService getWrappedService() {
		return _vwCompanyParentDetailsLocalService;
	}

	@Override
	public void setWrappedService(
		VwCompanyParentDetailsLocalService vwCompanyParentDetailsLocalService) {
		_vwCompanyParentDetailsLocalService = vwCompanyParentDetailsLocalService;
	}

	private VwCompanyParentDetailsLocalService _vwCompanyParentDetailsLocalService;
}