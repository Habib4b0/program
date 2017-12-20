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
 * Provides a wrapper for {@link IvldActualMasterLocalService}.
 *
 * @author
 * @see IvldActualMasterLocalService
 * @generated
 */
@ProviderType
public class IvldActualMasterLocalServiceWrapper
	implements IvldActualMasterLocalService,
		ServiceWrapper<IvldActualMasterLocalService> {
	public IvldActualMasterLocalServiceWrapper(
		IvldActualMasterLocalService ivldActualMasterLocalService) {
		_ivldActualMasterLocalService = ivldActualMasterLocalService;
	}

	/**
	* Adds the ivld actual master to the database. Also notifies the appropriate model listeners.
	*
	* @param ivldActualMaster the ivld actual master
	* @return the ivld actual master that was added
	*/
	@Override
	public com.stpl.app.model.IvldActualMaster addIvldActualMaster(
		com.stpl.app.model.IvldActualMaster ivldActualMaster) {
		return _ivldActualMasterLocalService.addIvldActualMaster(ivldActualMaster);
	}

	/**
	* Creates a new ivld actual master with the primary key. Does not add the ivld actual master to the database.
	*
	* @param ivldActualMasterSid the primary key for the new ivld actual master
	* @return the new ivld actual master
	*/
	@Override
	public com.stpl.app.model.IvldActualMaster createIvldActualMaster(
		int ivldActualMasterSid) {
		return _ivldActualMasterLocalService.createIvldActualMaster(ivldActualMasterSid);
	}

	/**
	* Deletes the ivld actual master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldActualMasterSid the primary key of the ivld actual master
	* @return the ivld actual master that was removed
	* @throws PortalException if a ivld actual master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.IvldActualMaster deleteIvldActualMaster(
		int ivldActualMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldActualMasterLocalService.deleteIvldActualMaster(ivldActualMasterSid);
	}

	/**
	* Deletes the ivld actual master from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldActualMaster the ivld actual master
	* @return the ivld actual master that was removed
	*/
	@Override
	public com.stpl.app.model.IvldActualMaster deleteIvldActualMaster(
		com.stpl.app.model.IvldActualMaster ivldActualMaster) {
		return _ivldActualMasterLocalService.deleteIvldActualMaster(ivldActualMaster);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldActualMasterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ivldActualMasterLocalService.dynamicQuery();
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
		return _ivldActualMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldActualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ivldActualMasterLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldActualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ivldActualMasterLocalService.dynamicQuery(dynamicQuery, start,
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
		return _ivldActualMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _ivldActualMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.IvldActualMaster fetchIvldActualMaster(
		int ivldActualMasterSid) {
		return _ivldActualMasterLocalService.fetchIvldActualMaster(ivldActualMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _ivldActualMasterLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _ivldActualMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the ivld actual master with the primary key.
	*
	* @param ivldActualMasterSid the primary key of the ivld actual master
	* @return the ivld actual master
	* @throws PortalException if a ivld actual master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.IvldActualMaster getIvldActualMaster(
		int ivldActualMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldActualMasterLocalService.getIvldActualMaster(ivldActualMasterSid);
	}

	/**
	* Returns a range of all the ivld actual masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldActualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld actual masters
	* @param end the upper bound of the range of ivld actual masters (not inclusive)
	* @return the range of ivld actual masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.IvldActualMaster> getIvldActualMasters(
		int start, int end) {
		return _ivldActualMasterLocalService.getIvldActualMasters(start, end);
	}

	/**
	* Returns the number of ivld actual masters.
	*
	* @return the number of ivld actual masters
	*/
	@Override
	public int getIvldActualMastersCount() {
		return _ivldActualMasterLocalService.getIvldActualMastersCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ivldActualMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldActualMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the ivld actual master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ivldActualMaster the ivld actual master
	* @return the ivld actual master that was updated
	*/
	@Override
	public com.stpl.app.model.IvldActualMaster updateIvldActualMaster(
		com.stpl.app.model.IvldActualMaster ivldActualMaster) {
		return _ivldActualMasterLocalService.updateIvldActualMaster(ivldActualMaster);
	}

	@Override
	public IvldActualMasterLocalService getWrappedService() {
		return _ivldActualMasterLocalService;
	}

	@Override
	public void setWrappedService(
		IvldActualMasterLocalService ivldActualMasterLocalService) {
		_ivldActualMasterLocalService = ivldActualMasterLocalService;
	}

	private IvldActualMasterLocalService _ivldActualMasterLocalService;
}