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
 * Provides a wrapper for {@link NmPpaProjectionMasterLocalService}.
 *
 * @author
 * @see NmPpaProjectionMasterLocalService
 * @generated
 */
@ProviderType
public class NmPpaProjectionMasterLocalServiceWrapper
	implements NmPpaProjectionMasterLocalService,
		ServiceWrapper<NmPpaProjectionMasterLocalService> {
	public NmPpaProjectionMasterLocalServiceWrapper(
		NmPpaProjectionMasterLocalService nmPpaProjectionMasterLocalService) {
		_nmPpaProjectionMasterLocalService = nmPpaProjectionMasterLocalService;
	}

	/**
	* Adds the nm ppa projection master to the database. Also notifies the appropriate model listeners.
	*
	* @param nmPpaProjectionMaster the nm ppa projection master
	* @return the nm ppa projection master that was added
	*/
	@Override
	public com.stpl.app.model.NmPpaProjectionMaster addNmPpaProjectionMaster(
		com.stpl.app.model.NmPpaProjectionMaster nmPpaProjectionMaster) {
		return _nmPpaProjectionMasterLocalService.addNmPpaProjectionMaster(nmPpaProjectionMaster);
	}

	/**
	* Creates a new nm ppa projection master with the primary key. Does not add the nm ppa projection master to the database.
	*
	* @param projectionDetailsSid the primary key for the new nm ppa projection master
	* @return the new nm ppa projection master
	*/
	@Override
	public com.stpl.app.model.NmPpaProjectionMaster createNmPpaProjectionMaster(
		int projectionDetailsSid) {
		return _nmPpaProjectionMasterLocalService.createNmPpaProjectionMaster(projectionDetailsSid);
	}

	/**
	* Deletes the nm ppa projection master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectionDetailsSid the primary key of the nm ppa projection master
	* @return the nm ppa projection master that was removed
	* @throws PortalException if a nm ppa projection master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.NmPpaProjectionMaster deleteNmPpaProjectionMaster(
		int projectionDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _nmPpaProjectionMasterLocalService.deleteNmPpaProjectionMaster(projectionDetailsSid);
	}

	/**
	* Deletes the nm ppa projection master from the database. Also notifies the appropriate model listeners.
	*
	* @param nmPpaProjectionMaster the nm ppa projection master
	* @return the nm ppa projection master that was removed
	*/
	@Override
	public com.stpl.app.model.NmPpaProjectionMaster deleteNmPpaProjectionMaster(
		com.stpl.app.model.NmPpaProjectionMaster nmPpaProjectionMaster) {
		return _nmPpaProjectionMasterLocalService.deleteNmPpaProjectionMaster(nmPpaProjectionMaster);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _nmPpaProjectionMasterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _nmPpaProjectionMasterLocalService.dynamicQuery();
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
		return _nmPpaProjectionMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _nmPpaProjectionMasterLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _nmPpaProjectionMasterLocalService.dynamicQuery(dynamicQuery,
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
		return _nmPpaProjectionMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _nmPpaProjectionMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.NmPpaProjectionMaster fetchNmPpaProjectionMaster(
		int projectionDetailsSid) {
		return _nmPpaProjectionMasterLocalService.fetchNmPpaProjectionMaster(projectionDetailsSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _nmPpaProjectionMasterLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _nmPpaProjectionMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the nm ppa projection master with the primary key.
	*
	* @param projectionDetailsSid the primary key of the nm ppa projection master
	* @return the nm ppa projection master
	* @throws PortalException if a nm ppa projection master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.NmPpaProjectionMaster getNmPpaProjectionMaster(
		int projectionDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _nmPpaProjectionMasterLocalService.getNmPpaProjectionMaster(projectionDetailsSid);
	}

	/**
	* Returns a range of all the nm ppa projection masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm ppa projection masters
	* @param end the upper bound of the range of nm ppa projection masters (not inclusive)
	* @return the range of nm ppa projection masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.NmPpaProjectionMaster> getNmPpaProjectionMasters(
		int start, int end) {
		return _nmPpaProjectionMasterLocalService.getNmPpaProjectionMasters(start,
			end);
	}

	/**
	* Returns the number of nm ppa projection masters.
	*
	* @return the number of nm ppa projection masters
	*/
	@Override
	public int getNmPpaProjectionMastersCount() {
		return _nmPpaProjectionMasterLocalService.getNmPpaProjectionMastersCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _nmPpaProjectionMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _nmPpaProjectionMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the nm ppa projection master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param nmPpaProjectionMaster the nm ppa projection master
	* @return the nm ppa projection master that was updated
	*/
	@Override
	public com.stpl.app.model.NmPpaProjectionMaster updateNmPpaProjectionMaster(
		com.stpl.app.model.NmPpaProjectionMaster nmPpaProjectionMaster) {
		return _nmPpaProjectionMasterLocalService.updateNmPpaProjectionMaster(nmPpaProjectionMaster);
	}

	@Override
	public NmPpaProjectionMasterLocalService getWrappedService() {
		return _nmPpaProjectionMasterLocalService;
	}

	@Override
	public void setWrappedService(
		NmPpaProjectionMasterLocalService nmPpaProjectionMasterLocalService) {
		_nmPpaProjectionMasterLocalService = nmPpaProjectionMasterLocalService;
	}

	private NmPpaProjectionMasterLocalService _nmPpaProjectionMasterLocalService;
}