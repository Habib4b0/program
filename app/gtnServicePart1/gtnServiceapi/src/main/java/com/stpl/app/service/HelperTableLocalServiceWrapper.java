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
 * Provides a wrapper for {@link HelperTableLocalService}.
 *
 * @author
 * @see HelperTableLocalService
 * @generated
 */
@ProviderType
public class HelperTableLocalServiceWrapper implements HelperTableLocalService,
	ServiceWrapper<HelperTableLocalService> {
	public HelperTableLocalServiceWrapper(
		HelperTableLocalService helperTableLocalService) {
		_helperTableLocalService = helperTableLocalService;
	}

	/**
	* Adds the helper table to the database. Also notifies the appropriate model listeners.
	*
	* @param helperTable the helper table
	* @return the helper table that was added
	*/
	@Override
	public com.stpl.app.model.HelperTable addHelperTable(
		com.stpl.app.model.HelperTable helperTable) {
		return _helperTableLocalService.addHelperTable(helperTable);
	}

	/**
	* Creates a new helper table with the primary key. Does not add the helper table to the database.
	*
	* @param helperTableSid the primary key for the new helper table
	* @return the new helper table
	*/
	@Override
	public com.stpl.app.model.HelperTable createHelperTable(int helperTableSid) {
		return _helperTableLocalService.createHelperTable(helperTableSid);
	}

	/**
	* Deletes the helper table from the database. Also notifies the appropriate model listeners.
	*
	* @param helperTable the helper table
	* @return the helper table that was removed
	*/
	@Override
	public com.stpl.app.model.HelperTable deleteHelperTable(
		com.stpl.app.model.HelperTable helperTable) {
		return _helperTableLocalService.deleteHelperTable(helperTable);
	}

	/**
	* Deletes the helper table with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param helperTableSid the primary key of the helper table
	* @return the helper table that was removed
	* @throws PortalException if a helper table with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.HelperTable deleteHelperTable(int helperTableSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _helperTableLocalService.deleteHelperTable(helperTableSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _helperTableLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _helperTableLocalService.dynamicQuery();
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
		return _helperTableLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HelperTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _helperTableLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HelperTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _helperTableLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _helperTableLocalService.dynamicQueryCount(dynamicQuery);
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
		return _helperTableLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public java.util.List executeSelectQuery(java.lang.String query) {
		return _helperTableLocalService.executeSelectQuery(query);
	}

	@Override
	public java.lang.Object executeUpdateEntitiy(java.lang.Object entity) {
		return _helperTableLocalService.executeUpdateEntitiy(entity);
	}

	@Override
	public void executeUpdateQuery(java.lang.String query) {
		_helperTableLocalService.executeUpdateQuery(query);
	}

	@Override
	public int executeUpdateQueryCount(java.lang.String query) {
		return _helperTableLocalService.executeUpdateQueryCount(query);
	}

	@Override
	public com.stpl.app.model.HelperTable fetchHelperTable(int helperTableSid) {
		return _helperTableLocalService.fetchHelperTable(helperTableSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _helperTableLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the helper table with the primary key.
	*
	* @param helperTableSid the primary key of the helper table
	* @return the helper table
	* @throws PortalException if a helper table with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.HelperTable getHelperTable(int helperTableSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _helperTableLocalService.getHelperTable(helperTableSid);
	}

	/**
	* Returns a range of all the helper tables.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HelperTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of helper tables
	* @param end the upper bound of the range of helper tables (not inclusive)
	* @return the range of helper tables
	*/
	@Override
	public java.util.List<com.stpl.app.model.HelperTable> getHelperTables(
		int start, int end) {
		return _helperTableLocalService.getHelperTables(start, end);
	}

	/**
	* Returns the number of helper tables.
	*
	* @return the number of helper tables
	*/
	@Override
	public int getHelperTablesCount() {
		return _helperTableLocalService.getHelperTablesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _helperTableLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _helperTableLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _helperTableLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the helper table in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param helperTable the helper table
	* @return the helper table that was updated
	*/
	@Override
	public com.stpl.app.model.HelperTable updateHelperTable(
		com.stpl.app.model.HelperTable helperTable) {
		return _helperTableLocalService.updateHelperTable(helperTable);
	}

	@Override
	public HelperTableLocalService getWrappedService() {
		return _helperTableLocalService;
	}

	@Override
	public void setWrappedService(
		HelperTableLocalService helperTableLocalService) {
		_helperTableLocalService = helperTableLocalService;
	}

	private HelperTableLocalService _helperTableLocalService;
}