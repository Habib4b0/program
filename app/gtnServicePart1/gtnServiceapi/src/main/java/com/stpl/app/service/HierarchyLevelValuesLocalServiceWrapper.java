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
 * Provides a wrapper for {@link HierarchyLevelValuesLocalService}.
 *
 * @author
 * @see HierarchyLevelValuesLocalService
 * @generated
 */
@ProviderType
public class HierarchyLevelValuesLocalServiceWrapper
	implements HierarchyLevelValuesLocalService,
		ServiceWrapper<HierarchyLevelValuesLocalService> {
	public HierarchyLevelValuesLocalServiceWrapper(
		HierarchyLevelValuesLocalService hierarchyLevelValuesLocalService) {
		_hierarchyLevelValuesLocalService = hierarchyLevelValuesLocalService;
	}

	/**
	* Adds the hierarchy level values to the database. Also notifies the appropriate model listeners.
	*
	* @param hierarchyLevelValues the hierarchy level values
	* @return the hierarchy level values that was added
	*/
	@Override
	public com.stpl.app.model.HierarchyLevelValues addHierarchyLevelValues(
		com.stpl.app.model.HierarchyLevelValues hierarchyLevelValues) {
		return _hierarchyLevelValuesLocalService.addHierarchyLevelValues(hierarchyLevelValues);
	}

	/**
	* Creates a new hierarchy level values with the primary key. Does not add the hierarchy level values to the database.
	*
	* @param hierarchyLevelValuesSid the primary key for the new hierarchy level values
	* @return the new hierarchy level values
	*/
	@Override
	public com.stpl.app.model.HierarchyLevelValues createHierarchyLevelValues(
		int hierarchyLevelValuesSid) {
		return _hierarchyLevelValuesLocalService.createHierarchyLevelValues(hierarchyLevelValuesSid);
	}

	/**
	* Deletes the hierarchy level values from the database. Also notifies the appropriate model listeners.
	*
	* @param hierarchyLevelValues the hierarchy level values
	* @return the hierarchy level values that was removed
	*/
	@Override
	public com.stpl.app.model.HierarchyLevelValues deleteHierarchyLevelValues(
		com.stpl.app.model.HierarchyLevelValues hierarchyLevelValues) {
		return _hierarchyLevelValuesLocalService.deleteHierarchyLevelValues(hierarchyLevelValues);
	}

	/**
	* Deletes the hierarchy level values with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hierarchyLevelValuesSid the primary key of the hierarchy level values
	* @return the hierarchy level values that was removed
	* @throws PortalException if a hierarchy level values with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.HierarchyLevelValues deleteHierarchyLevelValues(
		int hierarchyLevelValuesSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _hierarchyLevelValuesLocalService.deleteHierarchyLevelValues(hierarchyLevelValuesSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _hierarchyLevelValuesLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _hierarchyLevelValuesLocalService.dynamicQuery();
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
		return _hierarchyLevelValuesLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _hierarchyLevelValuesLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _hierarchyLevelValuesLocalService.dynamicQuery(dynamicQuery,
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
		return _hierarchyLevelValuesLocalService.dynamicQueryCount(dynamicQuery);
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
		return _hierarchyLevelValuesLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.HierarchyLevelValues fetchHierarchyLevelValues(
		int hierarchyLevelValuesSid) {
		return _hierarchyLevelValuesLocalService.fetchHierarchyLevelValues(hierarchyLevelValuesSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _hierarchyLevelValuesLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the hierarchy level values with the primary key.
	*
	* @param hierarchyLevelValuesSid the primary key of the hierarchy level values
	* @return the hierarchy level values
	* @throws PortalException if a hierarchy level values with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.HierarchyLevelValues getHierarchyLevelValues(
		int hierarchyLevelValuesSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _hierarchyLevelValuesLocalService.getHierarchyLevelValues(hierarchyLevelValuesSid);
	}

	/**
	* Returns a range of all the hierarchy level valueses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hierarchy level valueses
	* @param end the upper bound of the range of hierarchy level valueses (not inclusive)
	* @return the range of hierarchy level valueses
	*/
	@Override
	public java.util.List<com.stpl.app.model.HierarchyLevelValues> getHierarchyLevelValueses(
		int start, int end) {
		return _hierarchyLevelValuesLocalService.getHierarchyLevelValueses(start,
			end);
	}

	/**
	* Returns the number of hierarchy level valueses.
	*
	* @return the number of hierarchy level valueses
	*/
	@Override
	public int getHierarchyLevelValuesesCount() {
		return _hierarchyLevelValuesLocalService.getHierarchyLevelValuesesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _hierarchyLevelValuesLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _hierarchyLevelValuesLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _hierarchyLevelValuesLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the hierarchy level values in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param hierarchyLevelValues the hierarchy level values
	* @return the hierarchy level values that was updated
	*/
	@Override
	public com.stpl.app.model.HierarchyLevelValues updateHierarchyLevelValues(
		com.stpl.app.model.HierarchyLevelValues hierarchyLevelValues) {
		return _hierarchyLevelValuesLocalService.updateHierarchyLevelValues(hierarchyLevelValues);
	}

	@Override
	public HierarchyLevelValuesLocalService getWrappedService() {
		return _hierarchyLevelValuesLocalService;
	}

	@Override
	public void setWrappedService(
		HierarchyLevelValuesLocalService hierarchyLevelValuesLocalService) {
		_hierarchyLevelValuesLocalService = hierarchyLevelValuesLocalService;
	}

	private HierarchyLevelValuesLocalService _hierarchyLevelValuesLocalService;
}