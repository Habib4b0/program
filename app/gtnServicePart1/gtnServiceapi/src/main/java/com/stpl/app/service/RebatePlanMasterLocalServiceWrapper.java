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
 * Provides a wrapper for {@link RebatePlanMasterLocalService}.
 *
 * @author
 * @see RebatePlanMasterLocalService
 * @generated
 */
@ProviderType
public class RebatePlanMasterLocalServiceWrapper
	implements RebatePlanMasterLocalService,
		ServiceWrapper<RebatePlanMasterLocalService> {
	public RebatePlanMasterLocalServiceWrapper(
		RebatePlanMasterLocalService rebatePlanMasterLocalService) {
		_rebatePlanMasterLocalService = rebatePlanMasterLocalService;
	}

	/**
	* Adds the rebate plan master to the database. Also notifies the appropriate model listeners.
	*
	* @param rebatePlanMaster the rebate plan master
	* @return the rebate plan master that was added
	*/
	@Override
	public com.stpl.app.model.RebatePlanMaster addRebatePlanMaster(
		com.stpl.app.model.RebatePlanMaster rebatePlanMaster) {
		return _rebatePlanMasterLocalService.addRebatePlanMaster(rebatePlanMaster);
	}

	/**
	* Creates a new rebate plan master with the primary key. Does not add the rebate plan master to the database.
	*
	* @param rebatePlanMasterSid the primary key for the new rebate plan master
	* @return the new rebate plan master
	*/
	@Override
	public com.stpl.app.model.RebatePlanMaster createRebatePlanMaster(
		int rebatePlanMasterSid) {
		return _rebatePlanMasterLocalService.createRebatePlanMaster(rebatePlanMasterSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _rebatePlanMasterLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the rebate plan master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param rebatePlanMasterSid the primary key of the rebate plan master
	* @return the rebate plan master that was removed
	* @throws PortalException if a rebate plan master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.RebatePlanMaster deleteRebatePlanMaster(
		int rebatePlanMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _rebatePlanMasterLocalService.deleteRebatePlanMaster(rebatePlanMasterSid);
	}

	/**
	* Deletes the rebate plan master from the database. Also notifies the appropriate model listeners.
	*
	* @param rebatePlanMaster the rebate plan master
	* @return the rebate plan master that was removed
	*/
	@Override
	public com.stpl.app.model.RebatePlanMaster deleteRebatePlanMaster(
		com.stpl.app.model.RebatePlanMaster rebatePlanMaster) {
		return _rebatePlanMasterLocalService.deleteRebatePlanMaster(rebatePlanMaster);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _rebatePlanMasterLocalService.dynamicQuery();
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
		return _rebatePlanMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _rebatePlanMasterLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _rebatePlanMasterLocalService.dynamicQuery(dynamicQuery, start,
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
		return _rebatePlanMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _rebatePlanMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.RebatePlanMaster fetchRebatePlanMaster(
		int rebatePlanMasterSid) {
		return _rebatePlanMasterLocalService.fetchRebatePlanMaster(rebatePlanMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _rebatePlanMasterLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _rebatePlanMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _rebatePlanMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _rebatePlanMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the rebate plan master with the primary key.
	*
	* @param rebatePlanMasterSid the primary key of the rebate plan master
	* @return the rebate plan master
	* @throws PortalException if a rebate plan master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.RebatePlanMaster getRebatePlanMaster(
		int rebatePlanMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _rebatePlanMasterLocalService.getRebatePlanMaster(rebatePlanMasterSid);
	}

	/**
	* Returns a range of all the rebate plan masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rebate plan masters
	* @param end the upper bound of the range of rebate plan masters (not inclusive)
	* @return the range of rebate plan masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.RebatePlanMaster> getRebatePlanMasters(
		int start, int end) {
		return _rebatePlanMasterLocalService.getRebatePlanMasters(start, end);
	}

	/**
	* Returns the number of rebate plan masters.
	*
	* @return the number of rebate plan masters
	*/
	@Override
	public int getRebatePlanMastersCount() {
		return _rebatePlanMasterLocalService.getRebatePlanMastersCount();
	}

	/**
	* Updates the rebate plan master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param rebatePlanMaster the rebate plan master
	* @return the rebate plan master that was updated
	*/
	@Override
	public com.stpl.app.model.RebatePlanMaster updateRebatePlanMaster(
		com.stpl.app.model.RebatePlanMaster rebatePlanMaster) {
		return _rebatePlanMasterLocalService.updateRebatePlanMaster(rebatePlanMaster);
	}

	@Override
	public RebatePlanMasterLocalService getWrappedService() {
		return _rebatePlanMasterLocalService;
	}

	@Override
	public void setWrappedService(
		RebatePlanMasterLocalService rebatePlanMasterLocalService) {
		_rebatePlanMasterLocalService = rebatePlanMasterLocalService;
	}

	private RebatePlanMasterLocalService _rebatePlanMasterLocalService;
}