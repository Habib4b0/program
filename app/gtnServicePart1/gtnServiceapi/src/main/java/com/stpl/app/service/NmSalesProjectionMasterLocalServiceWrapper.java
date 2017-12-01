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
 * Provides a wrapper for {@link NmSalesProjectionMasterLocalService}.
 *
 * @author
 * @see NmSalesProjectionMasterLocalService
 * @generated
 */
@ProviderType
public class NmSalesProjectionMasterLocalServiceWrapper
	implements NmSalesProjectionMasterLocalService,
		ServiceWrapper<NmSalesProjectionMasterLocalService> {
	public NmSalesProjectionMasterLocalServiceWrapper(
		NmSalesProjectionMasterLocalService nmSalesProjectionMasterLocalService) {
		_nmSalesProjectionMasterLocalService = nmSalesProjectionMasterLocalService;
	}

	/**
	* Adds the nm sales projection master to the database. Also notifies the appropriate model listeners.
	*
	* @param nmSalesProjectionMaster the nm sales projection master
	* @return the nm sales projection master that was added
	*/
	@Override
	public com.stpl.app.model.NmSalesProjectionMaster addNmSalesProjectionMaster(
		com.stpl.app.model.NmSalesProjectionMaster nmSalesProjectionMaster) {
		return _nmSalesProjectionMasterLocalService.addNmSalesProjectionMaster(nmSalesProjectionMaster);
	}

	/**
	* Creates a new nm sales projection master with the primary key. Does not add the nm sales projection master to the database.
	*
	* @param projectionDetailsSid the primary key for the new nm sales projection master
	* @return the new nm sales projection master
	*/
	@Override
	public com.stpl.app.model.NmSalesProjectionMaster createNmSalesProjectionMaster(
		int projectionDetailsSid) {
		return _nmSalesProjectionMasterLocalService.createNmSalesProjectionMaster(projectionDetailsSid);
	}

	/**
	* Deletes the nm sales projection master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectionDetailsSid the primary key of the nm sales projection master
	* @return the nm sales projection master that was removed
	* @throws PortalException if a nm sales projection master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.NmSalesProjectionMaster deleteNmSalesProjectionMaster(
		int projectionDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _nmSalesProjectionMasterLocalService.deleteNmSalesProjectionMaster(projectionDetailsSid);
	}

	/**
	* Deletes the nm sales projection master from the database. Also notifies the appropriate model listeners.
	*
	* @param nmSalesProjectionMaster the nm sales projection master
	* @return the nm sales projection master that was removed
	*/
	@Override
	public com.stpl.app.model.NmSalesProjectionMaster deleteNmSalesProjectionMaster(
		com.stpl.app.model.NmSalesProjectionMaster nmSalesProjectionMaster) {
		return _nmSalesProjectionMasterLocalService.deleteNmSalesProjectionMaster(nmSalesProjectionMaster);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _nmSalesProjectionMasterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _nmSalesProjectionMasterLocalService.dynamicQuery();
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
		return _nmSalesProjectionMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _nmSalesProjectionMasterLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _nmSalesProjectionMasterLocalService.dynamicQuery(dynamicQuery,
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
		return _nmSalesProjectionMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _nmSalesProjectionMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.NmSalesProjectionMaster fetchNmSalesProjectionMaster(
		int projectionDetailsSid) {
		return _nmSalesProjectionMasterLocalService.fetchNmSalesProjectionMaster(projectionDetailsSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _nmSalesProjectionMasterLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _nmSalesProjectionMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the nm sales projection master with the primary key.
	*
	* @param projectionDetailsSid the primary key of the nm sales projection master
	* @return the nm sales projection master
	* @throws PortalException if a nm sales projection master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.NmSalesProjectionMaster getNmSalesProjectionMaster(
		int projectionDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _nmSalesProjectionMasterLocalService.getNmSalesProjectionMaster(projectionDetailsSid);
	}

	/**
	* Returns a range of all the nm sales projection masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm sales projection masters
	* @param end the upper bound of the range of nm sales projection masters (not inclusive)
	* @return the range of nm sales projection masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.NmSalesProjectionMaster> getNmSalesProjectionMasters(
		int start, int end) {
		return _nmSalesProjectionMasterLocalService.getNmSalesProjectionMasters(start,
			end);
	}

	/**
	* Returns the number of nm sales projection masters.
	*
	* @return the number of nm sales projection masters
	*/
	@Override
	public int getNmSalesProjectionMastersCount() {
		return _nmSalesProjectionMasterLocalService.getNmSalesProjectionMastersCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _nmSalesProjectionMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _nmSalesProjectionMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the nm sales projection master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param nmSalesProjectionMaster the nm sales projection master
	* @return the nm sales projection master that was updated
	*/
	@Override
	public com.stpl.app.model.NmSalesProjectionMaster updateNmSalesProjectionMaster(
		com.stpl.app.model.NmSalesProjectionMaster nmSalesProjectionMaster) {
		return _nmSalesProjectionMasterLocalService.updateNmSalesProjectionMaster(nmSalesProjectionMaster);
	}

	@Override
	public NmSalesProjectionMasterLocalService getWrappedService() {
		return _nmSalesProjectionMasterLocalService;
	}

	@Override
	public void setWrappedService(
		NmSalesProjectionMasterLocalService nmSalesProjectionMasterLocalService) {
		_nmSalesProjectionMasterLocalService = nmSalesProjectionMasterLocalService;
	}

	private NmSalesProjectionMasterLocalService _nmSalesProjectionMasterLocalService;
}