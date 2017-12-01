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
 * Provides a wrapper for {@link IvldLotMasterLocalService}.
 *
 * @author
 * @see IvldLotMasterLocalService
 * @generated
 */
@ProviderType
public class IvldLotMasterLocalServiceWrapper
	implements IvldLotMasterLocalService,
		ServiceWrapper<IvldLotMasterLocalService> {
	public IvldLotMasterLocalServiceWrapper(
		IvldLotMasterLocalService ivldLotMasterLocalService) {
		_ivldLotMasterLocalService = ivldLotMasterLocalService;
	}

	/**
	* Adds the ivld lot master to the database. Also notifies the appropriate model listeners.
	*
	* @param ivldLotMaster the ivld lot master
	* @return the ivld lot master that was added
	*/
	@Override
	public com.stpl.app.model.IvldLotMaster addIvldLotMaster(
		com.stpl.app.model.IvldLotMaster ivldLotMaster) {
		return _ivldLotMasterLocalService.addIvldLotMaster(ivldLotMaster);
	}

	/**
	* Creates a new ivld lot master with the primary key. Does not add the ivld lot master to the database.
	*
	* @param ivldLotMasterSid the primary key for the new ivld lot master
	* @return the new ivld lot master
	*/
	@Override
	public com.stpl.app.model.IvldLotMaster createIvldLotMaster(
		int ivldLotMasterSid) {
		return _ivldLotMasterLocalService.createIvldLotMaster(ivldLotMasterSid);
	}

	/**
	* Deletes the ivld lot master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldLotMasterSid the primary key of the ivld lot master
	* @return the ivld lot master that was removed
	* @throws PortalException if a ivld lot master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.IvldLotMaster deleteIvldLotMaster(
		int ivldLotMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldLotMasterLocalService.deleteIvldLotMaster(ivldLotMasterSid);
	}

	/**
	* Deletes the ivld lot master from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldLotMaster the ivld lot master
	* @return the ivld lot master that was removed
	*/
	@Override
	public com.stpl.app.model.IvldLotMaster deleteIvldLotMaster(
		com.stpl.app.model.IvldLotMaster ivldLotMaster) {
		return _ivldLotMasterLocalService.deleteIvldLotMaster(ivldLotMaster);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldLotMasterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ivldLotMasterLocalService.dynamicQuery();
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
		return _ivldLotMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldLotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ivldLotMasterLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldLotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ivldLotMasterLocalService.dynamicQuery(dynamicQuery, start,
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
		return _ivldLotMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _ivldLotMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.IvldLotMaster fetchIvldLotMaster(
		int ivldLotMasterSid) {
		return _ivldLotMasterLocalService.fetchIvldLotMaster(ivldLotMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _ivldLotMasterLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _ivldLotMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the ivld lot master with the primary key.
	*
	* @param ivldLotMasterSid the primary key of the ivld lot master
	* @return the ivld lot master
	* @throws PortalException if a ivld lot master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.IvldLotMaster getIvldLotMaster(
		int ivldLotMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldLotMasterLocalService.getIvldLotMaster(ivldLotMasterSid);
	}

	/**
	* Returns a range of all the ivld lot masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldLotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld lot masters
	* @param end the upper bound of the range of ivld lot masters (not inclusive)
	* @return the range of ivld lot masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.IvldLotMaster> getIvldLotMasters(
		int start, int end) {
		return _ivldLotMasterLocalService.getIvldLotMasters(start, end);
	}

	/**
	* Returns the number of ivld lot masters.
	*
	* @return the number of ivld lot masters
	*/
	@Override
	public int getIvldLotMastersCount() {
		return _ivldLotMasterLocalService.getIvldLotMastersCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ivldLotMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldLotMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the ivld lot master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ivldLotMaster the ivld lot master
	* @return the ivld lot master that was updated
	*/
	@Override
	public com.stpl.app.model.IvldLotMaster updateIvldLotMaster(
		com.stpl.app.model.IvldLotMaster ivldLotMaster) {
		return _ivldLotMasterLocalService.updateIvldLotMaster(ivldLotMaster);
	}

	@Override
	public IvldLotMasterLocalService getWrappedService() {
		return _ivldLotMasterLocalService;
	}

	@Override
	public void setWrappedService(
		IvldLotMasterLocalService ivldLotMasterLocalService) {
		_ivldLotMasterLocalService = ivldLotMasterLocalService;
	}

	private IvldLotMasterLocalService _ivldLotMasterLocalService;
}