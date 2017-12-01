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
 * Provides a wrapper for {@link StSelectionTableLocalService}.
 *
 * @author
 * @see StSelectionTableLocalService
 * @generated
 */
@ProviderType
public class StSelectionTableLocalServiceWrapper
	implements StSelectionTableLocalService,
		ServiceWrapper<StSelectionTableLocalService> {
	public StSelectionTableLocalServiceWrapper(
		StSelectionTableLocalService stSelectionTableLocalService) {
		_stSelectionTableLocalService = stSelectionTableLocalService;
	}

	/**
	* Adds the st selection table to the database. Also notifies the appropriate model listeners.
	*
	* @param stSelectionTable the st selection table
	* @return the st selection table that was added
	*/
	@Override
	public com.stpl.app.model.StSelectionTable addStSelectionTable(
		com.stpl.app.model.StSelectionTable stSelectionTable) {
		return _stSelectionTableLocalService.addStSelectionTable(stSelectionTable);
	}

	/**
	* Creates a new st selection table with the primary key. Does not add the st selection table to the database.
	*
	* @param stSelectionTablePK the primary key for the new st selection table
	* @return the new st selection table
	*/
	@Override
	public com.stpl.app.model.StSelectionTable createStSelectionTable(
		com.stpl.app.service.persistence.StSelectionTablePK stSelectionTablePK) {
		return _stSelectionTableLocalService.createStSelectionTable(stSelectionTablePK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stSelectionTableLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the st selection table from the database. Also notifies the appropriate model listeners.
	*
	* @param stSelectionTable the st selection table
	* @return the st selection table that was removed
	*/
	@Override
	public com.stpl.app.model.StSelectionTable deleteStSelectionTable(
		com.stpl.app.model.StSelectionTable stSelectionTable) {
		return _stSelectionTableLocalService.deleteStSelectionTable(stSelectionTable);
	}

	/**
	* Deletes the st selection table with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stSelectionTablePK the primary key of the st selection table
	* @return the st selection table that was removed
	* @throws PortalException if a st selection table with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.StSelectionTable deleteStSelectionTable(
		com.stpl.app.service.persistence.StSelectionTablePK stSelectionTablePK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stSelectionTableLocalService.deleteStSelectionTable(stSelectionTablePK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _stSelectionTableLocalService.dynamicQuery();
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
		return _stSelectionTableLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StSelectionTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stSelectionTableLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StSelectionTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stSelectionTableLocalService.dynamicQuery(dynamicQuery, start,
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
		return _stSelectionTableLocalService.dynamicQueryCount(dynamicQuery);
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
		return _stSelectionTableLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.StSelectionTable fetchStSelectionTable(
		com.stpl.app.service.persistence.StSelectionTablePK stSelectionTablePK) {
		return _stSelectionTableLocalService.fetchStSelectionTable(stSelectionTablePK);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _stSelectionTableLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stSelectionTableLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the st selection table with the primary key.
	*
	* @param stSelectionTablePK the primary key of the st selection table
	* @return the st selection table
	* @throws PortalException if a st selection table with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.StSelectionTable getStSelectionTable(
		com.stpl.app.service.persistence.StSelectionTablePK stSelectionTablePK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stSelectionTableLocalService.getStSelectionTable(stSelectionTablePK);
	}

	/**
	* Returns a range of all the st selection tables.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StSelectionTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st selection tables
	* @param end the upper bound of the range of st selection tables (not inclusive)
	* @return the range of st selection tables
	*/
	@Override
	public java.util.List<com.stpl.app.model.StSelectionTable> getStSelectionTables(
		int start, int end) {
		return _stSelectionTableLocalService.getStSelectionTables(start, end);
	}

	/**
	* Returns the number of st selection tables.
	*
	* @return the number of st selection tables
	*/
	@Override
	public int getStSelectionTablesCount() {
		return _stSelectionTableLocalService.getStSelectionTablesCount();
	}

	/**
	* Updates the st selection table in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param stSelectionTable the st selection table
	* @return the st selection table that was updated
	*/
	@Override
	public com.stpl.app.model.StSelectionTable updateStSelectionTable(
		com.stpl.app.model.StSelectionTable stSelectionTable) {
		return _stSelectionTableLocalService.updateStSelectionTable(stSelectionTable);
	}

	@Override
	public StSelectionTableLocalService getWrappedService() {
		return _stSelectionTableLocalService;
	}

	@Override
	public void setWrappedService(
		StSelectionTableLocalService stSelectionTableLocalService) {
		_stSelectionTableLocalService = stSelectionTableLocalService;
	}

	private StSelectionTableLocalService _stSelectionTableLocalService;
}