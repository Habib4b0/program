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
 * Provides a wrapper for {@link HistItemGroupLocalService}.
 *
 * @author
 * @see HistItemGroupLocalService
 * @generated
 */
@ProviderType
public class HistItemGroupLocalServiceWrapper
	implements HistItemGroupLocalService,
		ServiceWrapper<HistItemGroupLocalService> {
	public HistItemGroupLocalServiceWrapper(
		HistItemGroupLocalService histItemGroupLocalService) {
		_histItemGroupLocalService = histItemGroupLocalService;
	}

	/**
	* Adds the hist item group to the database. Also notifies the appropriate model listeners.
	*
	* @param histItemGroup the hist item group
	* @return the hist item group that was added
	*/
	@Override
	public com.stpl.app.model.HistItemGroup addHistItemGroup(
		com.stpl.app.model.HistItemGroup histItemGroup) {
		return _histItemGroupLocalService.addHistItemGroup(histItemGroup);
	}

	/**
	* Creates a new hist item group with the primary key. Does not add the hist item group to the database.
	*
	* @param histItemGroupPK the primary key for the new hist item group
	* @return the new hist item group
	*/
	@Override
	public com.stpl.app.model.HistItemGroup createHistItemGroup(
		com.stpl.app.service.persistence.HistItemGroupPK histItemGroupPK) {
		return _histItemGroupLocalService.createHistItemGroup(histItemGroupPK);
	}

	/**
	* Deletes the hist item group from the database. Also notifies the appropriate model listeners.
	*
	* @param histItemGroup the hist item group
	* @return the hist item group that was removed
	*/
	@Override
	public com.stpl.app.model.HistItemGroup deleteHistItemGroup(
		com.stpl.app.model.HistItemGroup histItemGroup) {
		return _histItemGroupLocalService.deleteHistItemGroup(histItemGroup);
	}

	/**
	* Deletes the hist item group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param histItemGroupPK the primary key of the hist item group
	* @return the hist item group that was removed
	* @throws PortalException if a hist item group with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.HistItemGroup deleteHistItemGroup(
		com.stpl.app.service.persistence.HistItemGroupPK histItemGroupPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _histItemGroupLocalService.deleteHistItemGroup(histItemGroupPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _histItemGroupLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _histItemGroupLocalService.dynamicQuery();
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
		return _histItemGroupLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _histItemGroupLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _histItemGroupLocalService.dynamicQuery(dynamicQuery, start,
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
		return _histItemGroupLocalService.dynamicQueryCount(dynamicQuery);
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
		return _histItemGroupLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.HistItemGroup fetchHistItemGroup(
		com.stpl.app.service.persistence.HistItemGroupPK histItemGroupPK) {
		return _histItemGroupLocalService.fetchHistItemGroup(histItemGroupPK);
	}

	/**
	* Returns the hist item group with the primary key.
	*
	* @param histItemGroupPK the primary key of the hist item group
	* @return the hist item group
	* @throws PortalException if a hist item group with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.HistItemGroup getHistItemGroup(
		com.stpl.app.service.persistence.HistItemGroupPK histItemGroupPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _histItemGroupLocalService.getHistItemGroup(histItemGroupPK);
	}

	/**
	* Returns a range of all the hist item groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist item groups
	* @param end the upper bound of the range of hist item groups (not inclusive)
	* @return the range of hist item groups
	*/
	@Override
	public java.util.List<com.stpl.app.model.HistItemGroup> getHistItemGroups(
		int start, int end) {
		return _histItemGroupLocalService.getHistItemGroups(start, end);
	}

	/**
	* Returns the number of hist item groups.
	*
	* @return the number of hist item groups
	*/
	@Override
	public int getHistItemGroupsCount() {
		return _histItemGroupLocalService.getHistItemGroupsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _histItemGroupLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _histItemGroupLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the hist item group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param histItemGroup the hist item group
	* @return the hist item group that was updated
	*/
	@Override
	public com.stpl.app.model.HistItemGroup updateHistItemGroup(
		com.stpl.app.model.HistItemGroup histItemGroup) {
		return _histItemGroupLocalService.updateHistItemGroup(histItemGroup);
	}

	@Override
	public HistItemGroupLocalService getWrappedService() {
		return _histItemGroupLocalService;
	}

	@Override
	public void setWrappedService(
		HistItemGroupLocalService histItemGroupLocalService) {
		_histItemGroupLocalService = histItemGroupLocalService;
	}

	private HistItemGroupLocalService _histItemGroupLocalService;
}