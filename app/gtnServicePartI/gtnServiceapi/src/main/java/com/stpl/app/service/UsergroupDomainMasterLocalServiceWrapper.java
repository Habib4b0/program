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
 * Provides a wrapper for {@link UsergroupDomainMasterLocalService}.
 *
 * @author
 * @see UsergroupDomainMasterLocalService
 * @generated
 */
@ProviderType
public class UsergroupDomainMasterLocalServiceWrapper
	implements UsergroupDomainMasterLocalService,
		ServiceWrapper<UsergroupDomainMasterLocalService> {
	public UsergroupDomainMasterLocalServiceWrapper(
		UsergroupDomainMasterLocalService usergroupDomainMasterLocalService) {
		_usergroupDomainMasterLocalService = usergroupDomainMasterLocalService;
	}

	/**
	* Adds the usergroup domain master to the database. Also notifies the appropriate model listeners.
	*
	* @param usergroupDomainMaster the usergroup domain master
	* @return the usergroup domain master that was added
	*/
	@Override
	public com.stpl.app.model.UsergroupDomainMaster addUsergroupDomainMaster(
		com.stpl.app.model.UsergroupDomainMaster usergroupDomainMaster) {
		return _usergroupDomainMasterLocalService.addUsergroupDomainMaster(usergroupDomainMaster);
	}

	/**
	* Creates a new usergroup domain master with the primary key. Does not add the usergroup domain master to the database.
	*
	* @param usergroupDomainSid the primary key for the new usergroup domain master
	* @return the new usergroup domain master
	*/
	@Override
	public com.stpl.app.model.UsergroupDomainMaster createUsergroupDomainMaster(
		int usergroupDomainSid) {
		return _usergroupDomainMasterLocalService.createUsergroupDomainMaster(usergroupDomainSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _usergroupDomainMasterLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the usergroup domain master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param usergroupDomainSid the primary key of the usergroup domain master
	* @return the usergroup domain master that was removed
	* @throws PortalException if a usergroup domain master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.UsergroupDomainMaster deleteUsergroupDomainMaster(
		int usergroupDomainSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _usergroupDomainMasterLocalService.deleteUsergroupDomainMaster(usergroupDomainSid);
	}

	/**
	* Deletes the usergroup domain master from the database. Also notifies the appropriate model listeners.
	*
	* @param usergroupDomainMaster the usergroup domain master
	* @return the usergroup domain master that was removed
	*/
	@Override
	public com.stpl.app.model.UsergroupDomainMaster deleteUsergroupDomainMaster(
		com.stpl.app.model.UsergroupDomainMaster usergroupDomainMaster) {
		return _usergroupDomainMasterLocalService.deleteUsergroupDomainMaster(usergroupDomainMaster);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _usergroupDomainMasterLocalService.dynamicQuery();
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
		return _usergroupDomainMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.UsergroupDomainMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _usergroupDomainMasterLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.UsergroupDomainMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _usergroupDomainMasterLocalService.dynamicQuery(dynamicQuery,
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
		return _usergroupDomainMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _usergroupDomainMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.UsergroupDomainMaster fetchUsergroupDomainMaster(
		int usergroupDomainSid) {
		return _usergroupDomainMasterLocalService.fetchUsergroupDomainMaster(usergroupDomainSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _usergroupDomainMasterLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _usergroupDomainMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _usergroupDomainMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _usergroupDomainMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the usergroup domain master with the primary key.
	*
	* @param usergroupDomainSid the primary key of the usergroup domain master
	* @return the usergroup domain master
	* @throws PortalException if a usergroup domain master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.UsergroupDomainMaster getUsergroupDomainMaster(
		int usergroupDomainSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _usergroupDomainMasterLocalService.getUsergroupDomainMaster(usergroupDomainSid);
	}

	/**
	* Returns a range of all the usergroup domain masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.UsergroupDomainMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of usergroup domain masters
	* @param end the upper bound of the range of usergroup domain masters (not inclusive)
	* @return the range of usergroup domain masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.UsergroupDomainMaster> getUsergroupDomainMasters(
		int start, int end) {
		return _usergroupDomainMasterLocalService.getUsergroupDomainMasters(start,
			end);
	}

	/**
	* Returns the number of usergroup domain masters.
	*
	* @return the number of usergroup domain masters
	*/
	@Override
	public int getUsergroupDomainMastersCount() {
		return _usergroupDomainMasterLocalService.getUsergroupDomainMastersCount();
	}

	/**
	* Updates the usergroup domain master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param usergroupDomainMaster the usergroup domain master
	* @return the usergroup domain master that was updated
	*/
	@Override
	public com.stpl.app.model.UsergroupDomainMaster updateUsergroupDomainMaster(
		com.stpl.app.model.UsergroupDomainMaster usergroupDomainMaster) {
		return _usergroupDomainMasterLocalService.updateUsergroupDomainMaster(usergroupDomainMaster);
	}

	@Override
	public UsergroupDomainMasterLocalService getWrappedService() {
		return _usergroupDomainMasterLocalService;
	}

	@Override
	public void setWrappedService(
		UsergroupDomainMasterLocalService usergroupDomainMasterLocalService) {
		_usergroupDomainMasterLocalService = usergroupDomainMasterLocalService;
	}

	private UsergroupDomainMasterLocalService _usergroupDomainMasterLocalService;
}