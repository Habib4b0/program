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
 * Provides a wrapper for {@link VwUserTablesLocalService}.
 *
 * @author
 * @see VwUserTablesLocalService
 * @generated
 */
@ProviderType
public class VwUserTablesLocalServiceWrapper implements VwUserTablesLocalService,
	ServiceWrapper<VwUserTablesLocalService> {
	public VwUserTablesLocalServiceWrapper(
		VwUserTablesLocalService vwUserTablesLocalService) {
		_vwUserTablesLocalService = vwUserTablesLocalService;
	}

	/**
	* Adds the vw user tables to the database. Also notifies the appropriate model listeners.
	*
	* @param vwUserTables the vw user tables
	* @return the vw user tables that was added
	*/
	@Override
	public com.stpl.app.model.VwUserTables addVwUserTables(
		com.stpl.app.model.VwUserTables vwUserTables) {
		return _vwUserTablesLocalService.addVwUserTables(vwUserTables);
	}

	/**
	* Creates a new vw user tables with the primary key. Does not add the vw user tables to the database.
	*
	* @param uniqueId the primary key for the new vw user tables
	* @return the new vw user tables
	*/
	@Override
	public com.stpl.app.model.VwUserTables createVwUserTables(int uniqueId) {
		return _vwUserTablesLocalService.createVwUserTables(uniqueId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _vwUserTablesLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the vw user tables with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param uniqueId the primary key of the vw user tables
	* @return the vw user tables that was removed
	* @throws PortalException if a vw user tables with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.VwUserTables deleteVwUserTables(int uniqueId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _vwUserTablesLocalService.deleteVwUserTables(uniqueId);
	}

	/**
	* Deletes the vw user tables from the database. Also notifies the appropriate model listeners.
	*
	* @param vwUserTables the vw user tables
	* @return the vw user tables that was removed
	*/
	@Override
	public com.stpl.app.model.VwUserTables deleteVwUserTables(
		com.stpl.app.model.VwUserTables vwUserTables) {
		return _vwUserTablesLocalService.deleteVwUserTables(vwUserTables);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _vwUserTablesLocalService.dynamicQuery();
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
		return _vwUserTablesLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwUserTablesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _vwUserTablesLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwUserTablesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _vwUserTablesLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _vwUserTablesLocalService.dynamicQueryCount(dynamicQuery);
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
		return _vwUserTablesLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.VwUserTables fetchVwUserTables(int uniqueId) {
		return _vwUserTablesLocalService.fetchVwUserTables(uniqueId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _vwUserTablesLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _vwUserTablesLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _vwUserTablesLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _vwUserTablesLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the vw user tables with the primary key.
	*
	* @param uniqueId the primary key of the vw user tables
	* @return the vw user tables
	* @throws PortalException if a vw user tables with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.VwUserTables getVwUserTables(int uniqueId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _vwUserTablesLocalService.getVwUserTables(uniqueId);
	}

	/**
	* Returns a range of all the vw user tableses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwUserTablesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw user tableses
	* @param end the upper bound of the range of vw user tableses (not inclusive)
	* @return the range of vw user tableses
	*/
	@Override
	public java.util.List<com.stpl.app.model.VwUserTables> getVwUserTableses(
		int start, int end) {
		return _vwUserTablesLocalService.getVwUserTableses(start, end);
	}

	/**
	* Returns the number of vw user tableses.
	*
	* @return the number of vw user tableses
	*/
	@Override
	public int getVwUserTablesesCount() {
		return _vwUserTablesLocalService.getVwUserTablesesCount();
	}

	/**
	* Updates the vw user tables in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param vwUserTables the vw user tables
	* @return the vw user tables that was updated
	*/
	@Override
	public com.stpl.app.model.VwUserTables updateVwUserTables(
		com.stpl.app.model.VwUserTables vwUserTables) {
		return _vwUserTablesLocalService.updateVwUserTables(vwUserTables);
	}

	@Override
	public VwUserTablesLocalService getWrappedService() {
		return _vwUserTablesLocalService;
	}

	@Override
	public void setWrappedService(
		VwUserTablesLocalService vwUserTablesLocalService) {
		_vwUserTablesLocalService = vwUserTablesLocalService;
	}

	private VwUserTablesLocalService _vwUserTablesLocalService;
}