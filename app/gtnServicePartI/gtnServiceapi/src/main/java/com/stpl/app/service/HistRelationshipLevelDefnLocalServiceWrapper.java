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
 * Provides a wrapper for {@link HistRelationshipLevelDefnLocalService}.
 *
 * @author
 * @see HistRelationshipLevelDefnLocalService
 * @generated
 */
@ProviderType
public class HistRelationshipLevelDefnLocalServiceWrapper
	implements HistRelationshipLevelDefnLocalService,
		ServiceWrapper<HistRelationshipLevelDefnLocalService> {
	public HistRelationshipLevelDefnLocalServiceWrapper(
		HistRelationshipLevelDefnLocalService histRelationshipLevelDefnLocalService) {
		_histRelationshipLevelDefnLocalService = histRelationshipLevelDefnLocalService;
	}

	/**
	* Adds the hist relationship level defn to the database. Also notifies the appropriate model listeners.
	*
	* @param histRelationshipLevelDefn the hist relationship level defn
	* @return the hist relationship level defn that was added
	*/
	@Override
	public com.stpl.app.model.HistRelationshipLevelDefn addHistRelationshipLevelDefn(
		com.stpl.app.model.HistRelationshipLevelDefn histRelationshipLevelDefn) {
		return _histRelationshipLevelDefnLocalService.addHistRelationshipLevelDefn(histRelationshipLevelDefn);
	}

	/**
	* Creates a new hist relationship level defn with the primary key. Does not add the hist relationship level defn to the database.
	*
	* @param histRelationshipLevelDefnPK the primary key for the new hist relationship level defn
	* @return the new hist relationship level defn
	*/
	@Override
	public com.stpl.app.model.HistRelationshipLevelDefn createHistRelationshipLevelDefn(
		com.stpl.app.service.persistence.HistRelationshipLevelDefnPK histRelationshipLevelDefnPK) {
		return _histRelationshipLevelDefnLocalService.createHistRelationshipLevelDefn(histRelationshipLevelDefnPK);
	}

	/**
	* Deletes the hist relationship level defn from the database. Also notifies the appropriate model listeners.
	*
	* @param histRelationshipLevelDefn the hist relationship level defn
	* @return the hist relationship level defn that was removed
	*/
	@Override
	public com.stpl.app.model.HistRelationshipLevelDefn deleteHistRelationshipLevelDefn(
		com.stpl.app.model.HistRelationshipLevelDefn histRelationshipLevelDefn) {
		return _histRelationshipLevelDefnLocalService.deleteHistRelationshipLevelDefn(histRelationshipLevelDefn);
	}

	/**
	* Deletes the hist relationship level defn with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param histRelationshipLevelDefnPK the primary key of the hist relationship level defn
	* @return the hist relationship level defn that was removed
	* @throws PortalException if a hist relationship level defn with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.HistRelationshipLevelDefn deleteHistRelationshipLevelDefn(
		com.stpl.app.service.persistence.HistRelationshipLevelDefnPK histRelationshipLevelDefnPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _histRelationshipLevelDefnLocalService.deleteHistRelationshipLevelDefn(histRelationshipLevelDefnPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _histRelationshipLevelDefnLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _histRelationshipLevelDefnLocalService.dynamicQuery();
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
		return _histRelationshipLevelDefnLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistRelationshipLevelDefnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _histRelationshipLevelDefnLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistRelationshipLevelDefnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _histRelationshipLevelDefnLocalService.dynamicQuery(dynamicQuery,
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
		return _histRelationshipLevelDefnLocalService.dynamicQueryCount(dynamicQuery);
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
		return _histRelationshipLevelDefnLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.HistRelationshipLevelDefn fetchHistRelationshipLevelDefn(
		com.stpl.app.service.persistence.HistRelationshipLevelDefnPK histRelationshipLevelDefnPK) {
		return _histRelationshipLevelDefnLocalService.fetchHistRelationshipLevelDefn(histRelationshipLevelDefnPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _histRelationshipLevelDefnLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the hist relationship level defn with the primary key.
	*
	* @param histRelationshipLevelDefnPK the primary key of the hist relationship level defn
	* @return the hist relationship level defn
	* @throws PortalException if a hist relationship level defn with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.HistRelationshipLevelDefn getHistRelationshipLevelDefn(
		com.stpl.app.service.persistence.HistRelationshipLevelDefnPK histRelationshipLevelDefnPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _histRelationshipLevelDefnLocalService.getHistRelationshipLevelDefn(histRelationshipLevelDefnPK);
	}

	/**
	* Returns a range of all the hist relationship level defns.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistRelationshipLevelDefnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist relationship level defns
	* @param end the upper bound of the range of hist relationship level defns (not inclusive)
	* @return the range of hist relationship level defns
	*/
	@Override
	public java.util.List<com.stpl.app.model.HistRelationshipLevelDefn> getHistRelationshipLevelDefns(
		int start, int end) {
		return _histRelationshipLevelDefnLocalService.getHistRelationshipLevelDefns(start,
			end);
	}

	/**
	* Returns the number of hist relationship level defns.
	*
	* @return the number of hist relationship level defns
	*/
	@Override
	public int getHistRelationshipLevelDefnsCount() {
		return _histRelationshipLevelDefnLocalService.getHistRelationshipLevelDefnsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _histRelationshipLevelDefnLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _histRelationshipLevelDefnLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _histRelationshipLevelDefnLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the hist relationship level defn in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param histRelationshipLevelDefn the hist relationship level defn
	* @return the hist relationship level defn that was updated
	*/
	@Override
	public com.stpl.app.model.HistRelationshipLevelDefn updateHistRelationshipLevelDefn(
		com.stpl.app.model.HistRelationshipLevelDefn histRelationshipLevelDefn) {
		return _histRelationshipLevelDefnLocalService.updateHistRelationshipLevelDefn(histRelationshipLevelDefn);
	}

	@Override
	public HistRelationshipLevelDefnLocalService getWrappedService() {
		return _histRelationshipLevelDefnLocalService;
	}

	@Override
	public void setWrappedService(
		HistRelationshipLevelDefnLocalService histRelationshipLevelDefnLocalService) {
		_histRelationshipLevelDefnLocalService = histRelationshipLevelDefnLocalService;
	}

	private HistRelationshipLevelDefnLocalService _histRelationshipLevelDefnLocalService;
}