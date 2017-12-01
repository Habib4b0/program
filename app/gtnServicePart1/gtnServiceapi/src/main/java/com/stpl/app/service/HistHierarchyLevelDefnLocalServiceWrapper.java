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
 * Provides a wrapper for {@link HistHierarchyLevelDefnLocalService}.
 *
 * @author
 * @see HistHierarchyLevelDefnLocalService
 * @generated
 */
@ProviderType
public class HistHierarchyLevelDefnLocalServiceWrapper
	implements HistHierarchyLevelDefnLocalService,
		ServiceWrapper<HistHierarchyLevelDefnLocalService> {
	public HistHierarchyLevelDefnLocalServiceWrapper(
		HistHierarchyLevelDefnLocalService histHierarchyLevelDefnLocalService) {
		_histHierarchyLevelDefnLocalService = histHierarchyLevelDefnLocalService;
	}

	/**
	* Adds the hist hierarchy level defn to the database. Also notifies the appropriate model listeners.
	*
	* @param histHierarchyLevelDefn the hist hierarchy level defn
	* @return the hist hierarchy level defn that was added
	*/
	@Override
	public com.stpl.app.model.HistHierarchyLevelDefn addHistHierarchyLevelDefn(
		com.stpl.app.model.HistHierarchyLevelDefn histHierarchyLevelDefn) {
		return _histHierarchyLevelDefnLocalService.addHistHierarchyLevelDefn(histHierarchyLevelDefn);
	}

	/**
	* Creates a new hist hierarchy level defn with the primary key. Does not add the hist hierarchy level defn to the database.
	*
	* @param histHierarchyLevelDefnPK the primary key for the new hist hierarchy level defn
	* @return the new hist hierarchy level defn
	*/
	@Override
	public com.stpl.app.model.HistHierarchyLevelDefn createHistHierarchyLevelDefn(
		com.stpl.app.service.persistence.HistHierarchyLevelDefnPK histHierarchyLevelDefnPK) {
		return _histHierarchyLevelDefnLocalService.createHistHierarchyLevelDefn(histHierarchyLevelDefnPK);
	}

	/**
	* Deletes the hist hierarchy level defn from the database. Also notifies the appropriate model listeners.
	*
	* @param histHierarchyLevelDefn the hist hierarchy level defn
	* @return the hist hierarchy level defn that was removed
	*/
	@Override
	public com.stpl.app.model.HistHierarchyLevelDefn deleteHistHierarchyLevelDefn(
		com.stpl.app.model.HistHierarchyLevelDefn histHierarchyLevelDefn) {
		return _histHierarchyLevelDefnLocalService.deleteHistHierarchyLevelDefn(histHierarchyLevelDefn);
	}

	/**
	* Deletes the hist hierarchy level defn with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param histHierarchyLevelDefnPK the primary key of the hist hierarchy level defn
	* @return the hist hierarchy level defn that was removed
	* @throws PortalException if a hist hierarchy level defn with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.HistHierarchyLevelDefn deleteHistHierarchyLevelDefn(
		com.stpl.app.service.persistence.HistHierarchyLevelDefnPK histHierarchyLevelDefnPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _histHierarchyLevelDefnLocalService.deleteHistHierarchyLevelDefn(histHierarchyLevelDefnPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _histHierarchyLevelDefnLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _histHierarchyLevelDefnLocalService.dynamicQuery();
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
		return _histHierarchyLevelDefnLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistHierarchyLevelDefnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _histHierarchyLevelDefnLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistHierarchyLevelDefnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _histHierarchyLevelDefnLocalService.dynamicQuery(dynamicQuery,
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
		return _histHierarchyLevelDefnLocalService.dynamicQueryCount(dynamicQuery);
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
		return _histHierarchyLevelDefnLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.HistHierarchyLevelDefn fetchHistHierarchyLevelDefn(
		com.stpl.app.service.persistence.HistHierarchyLevelDefnPK histHierarchyLevelDefnPK) {
		return _histHierarchyLevelDefnLocalService.fetchHistHierarchyLevelDefn(histHierarchyLevelDefnPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _histHierarchyLevelDefnLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the hist hierarchy level defn with the primary key.
	*
	* @param histHierarchyLevelDefnPK the primary key of the hist hierarchy level defn
	* @return the hist hierarchy level defn
	* @throws PortalException if a hist hierarchy level defn with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.HistHierarchyLevelDefn getHistHierarchyLevelDefn(
		com.stpl.app.service.persistence.HistHierarchyLevelDefnPK histHierarchyLevelDefnPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _histHierarchyLevelDefnLocalService.getHistHierarchyLevelDefn(histHierarchyLevelDefnPK);
	}

	/**
	* Returns a range of all the hist hierarchy level defns.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistHierarchyLevelDefnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist hierarchy level defns
	* @param end the upper bound of the range of hist hierarchy level defns (not inclusive)
	* @return the range of hist hierarchy level defns
	*/
	@Override
	public java.util.List<com.stpl.app.model.HistHierarchyLevelDefn> getHistHierarchyLevelDefns(
		int start, int end) {
		return _histHierarchyLevelDefnLocalService.getHistHierarchyLevelDefns(start,
			end);
	}

	/**
	* Returns the number of hist hierarchy level defns.
	*
	* @return the number of hist hierarchy level defns
	*/
	@Override
	public int getHistHierarchyLevelDefnsCount() {
		return _histHierarchyLevelDefnLocalService.getHistHierarchyLevelDefnsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _histHierarchyLevelDefnLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _histHierarchyLevelDefnLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _histHierarchyLevelDefnLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the hist hierarchy level defn in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param histHierarchyLevelDefn the hist hierarchy level defn
	* @return the hist hierarchy level defn that was updated
	*/
	@Override
	public com.stpl.app.model.HistHierarchyLevelDefn updateHistHierarchyLevelDefn(
		com.stpl.app.model.HistHierarchyLevelDefn histHierarchyLevelDefn) {
		return _histHierarchyLevelDefnLocalService.updateHistHierarchyLevelDefn(histHierarchyLevelDefn);
	}

	@Override
	public HistHierarchyLevelDefnLocalService getWrappedService() {
		return _histHierarchyLevelDefnLocalService;
	}

	@Override
	public void setWrappedService(
		HistHierarchyLevelDefnLocalService histHierarchyLevelDefnLocalService) {
		_histHierarchyLevelDefnLocalService = histHierarchyLevelDefnLocalService;
	}

	private HistHierarchyLevelDefnLocalService _histHierarchyLevelDefnLocalService;
}