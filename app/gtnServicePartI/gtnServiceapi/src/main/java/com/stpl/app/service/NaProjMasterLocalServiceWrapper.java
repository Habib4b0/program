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
 * Provides a wrapper for {@link NaProjMasterLocalService}.
 *
 * @author
 * @see NaProjMasterLocalService
 * @generated
 */
@ProviderType
public class NaProjMasterLocalServiceWrapper implements NaProjMasterLocalService,
	ServiceWrapper<NaProjMasterLocalService> {
	public NaProjMasterLocalServiceWrapper(
		NaProjMasterLocalService naProjMasterLocalService) {
		_naProjMasterLocalService = naProjMasterLocalService;
	}

	/**
	* Adds the na proj master to the database. Also notifies the appropriate model listeners.
	*
	* @param naProjMaster the na proj master
	* @return the na proj master that was added
	*/
	@Override
	public com.stpl.app.model.NaProjMaster addNaProjMaster(
		com.stpl.app.model.NaProjMaster naProjMaster) {
		return _naProjMasterLocalService.addNaProjMaster(naProjMaster);
	}

	/**
	* Creates a new na proj master with the primary key. Does not add the na proj master to the database.
	*
	* @param naProjMasterSid the primary key for the new na proj master
	* @return the new na proj master
	*/
	@Override
	public com.stpl.app.model.NaProjMaster createNaProjMaster(
		int naProjMasterSid) {
		return _naProjMasterLocalService.createNaProjMaster(naProjMasterSid);
	}

	/**
	* Deletes the na proj master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param naProjMasterSid the primary key of the na proj master
	* @return the na proj master that was removed
	* @throws PortalException if a na proj master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.NaProjMaster deleteNaProjMaster(
		int naProjMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _naProjMasterLocalService.deleteNaProjMaster(naProjMasterSid);
	}

	/**
	* Deletes the na proj master from the database. Also notifies the appropriate model listeners.
	*
	* @param naProjMaster the na proj master
	* @return the na proj master that was removed
	*/
	@Override
	public com.stpl.app.model.NaProjMaster deleteNaProjMaster(
		com.stpl.app.model.NaProjMaster naProjMaster) {
		return _naProjMasterLocalService.deleteNaProjMaster(naProjMaster);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _naProjMasterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _naProjMasterLocalService.dynamicQuery();
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
		return _naProjMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NaProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _naProjMasterLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NaProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _naProjMasterLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _naProjMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _naProjMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.NaProjMaster fetchNaProjMaster(
		int naProjMasterSid) {
		return _naProjMasterLocalService.fetchNaProjMaster(naProjMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _naProjMasterLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _naProjMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the na proj master with the primary key.
	*
	* @param naProjMasterSid the primary key of the na proj master
	* @return the na proj master
	* @throws PortalException if a na proj master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.NaProjMaster getNaProjMaster(int naProjMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _naProjMasterLocalService.getNaProjMaster(naProjMasterSid);
	}

	/**
	* Returns a range of all the na proj masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NaProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of na proj masters
	* @param end the upper bound of the range of na proj masters (not inclusive)
	* @return the range of na proj masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.NaProjMaster> getNaProjMasters(
		int start, int end) {
		return _naProjMasterLocalService.getNaProjMasters(start, end);
	}

	/**
	* Returns the number of na proj masters.
	*
	* @return the number of na proj masters
	*/
	@Override
	public int getNaProjMastersCount() {
		return _naProjMasterLocalService.getNaProjMastersCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _naProjMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _naProjMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the na proj master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param naProjMaster the na proj master
	* @return the na proj master that was updated
	*/
	@Override
	public com.stpl.app.model.NaProjMaster updateNaProjMaster(
		com.stpl.app.model.NaProjMaster naProjMaster) {
		return _naProjMasterLocalService.updateNaProjMaster(naProjMaster);
	}

	@Override
	public NaProjMasterLocalService getWrappedService() {
		return _naProjMasterLocalService;
	}

	@Override
	public void setWrappedService(
		NaProjMasterLocalService naProjMasterLocalService) {
		_naProjMasterLocalService = naProjMasterLocalService;
	}

	private NaProjMasterLocalService _naProjMasterLocalService;
}