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
 * Provides a wrapper for {@link LotMasterLocalService}.
 *
 * @author
 * @see LotMasterLocalService
 * @generated
 */
@ProviderType
public class LotMasterLocalServiceWrapper implements LotMasterLocalService,
	ServiceWrapper<LotMasterLocalService> {
	public LotMasterLocalServiceWrapper(
		LotMasterLocalService lotMasterLocalService) {
		_lotMasterLocalService = lotMasterLocalService;
	}

	/**
	* Adds the lot master to the database. Also notifies the appropriate model listeners.
	*
	* @param lotMaster the lot master
	* @return the lot master that was added
	*/
	@Override
	public com.stpl.app.model.LotMaster addLotMaster(
		com.stpl.app.model.LotMaster lotMaster) {
		return _lotMasterLocalService.addLotMaster(lotMaster);
	}

	/**
	* Creates a new lot master with the primary key. Does not add the lot master to the database.
	*
	* @param lotMasterSid the primary key for the new lot master
	* @return the new lot master
	*/
	@Override
	public com.stpl.app.model.LotMaster createLotMaster(int lotMasterSid) {
		return _lotMasterLocalService.createLotMaster(lotMasterSid);
	}

	/**
	* Deletes the lot master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lotMasterSid the primary key of the lot master
	* @return the lot master that was removed
	* @throws PortalException if a lot master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.LotMaster deleteLotMaster(int lotMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lotMasterLocalService.deleteLotMaster(lotMasterSid);
	}

	/**
	* Deletes the lot master from the database. Also notifies the appropriate model listeners.
	*
	* @param lotMaster the lot master
	* @return the lot master that was removed
	*/
	@Override
	public com.stpl.app.model.LotMaster deleteLotMaster(
		com.stpl.app.model.LotMaster lotMaster) {
		return _lotMasterLocalService.deleteLotMaster(lotMaster);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lotMasterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _lotMasterLocalService.dynamicQuery();
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
		return _lotMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _lotMasterLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _lotMasterLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _lotMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _lotMasterLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.stpl.app.model.LotMaster fetchLotMaster(int lotMasterSid) {
		return _lotMasterLocalService.fetchLotMaster(lotMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _lotMasterLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _lotMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the lot master with the primary key.
	*
	* @param lotMasterSid the primary key of the lot master
	* @return the lot master
	* @throws PortalException if a lot master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.LotMaster getLotMaster(int lotMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lotMasterLocalService.getLotMaster(lotMasterSid);
	}

	/**
	* Returns a range of all the lot masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of lot masters
	* @param end the upper bound of the range of lot masters (not inclusive)
	* @return the range of lot masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.LotMaster> getLotMasters(
		int start, int end) {
		return _lotMasterLocalService.getLotMasters(start, end);
	}

	/**
	* Returns the number of lot masters.
	*
	* @return the number of lot masters
	*/
	@Override
	public int getLotMastersCount() {
		return _lotMasterLocalService.getLotMastersCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _lotMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lotMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the lot master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lotMaster the lot master
	* @return the lot master that was updated
	*/
	@Override
	public com.stpl.app.model.LotMaster updateLotMaster(
		com.stpl.app.model.LotMaster lotMaster) {
		return _lotMasterLocalService.updateLotMaster(lotMaster);
	}

	@Override
	public LotMasterLocalService getWrappedService() {
		return _lotMasterLocalService;
	}

	@Override
	public void setWrappedService(LotMasterLocalService lotMasterLocalService) {
		_lotMasterLocalService = lotMasterLocalService;
	}

	private LotMasterLocalService _lotMasterLocalService;
}