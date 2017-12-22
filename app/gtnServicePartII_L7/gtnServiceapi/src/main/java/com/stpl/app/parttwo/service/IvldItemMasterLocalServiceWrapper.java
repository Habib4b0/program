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

package com.stpl.app.parttwo.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link IvldItemMasterLocalService}.
 *
 * @author
 * @see IvldItemMasterLocalService
 * @generated
 */
@ProviderType
public class IvldItemMasterLocalServiceWrapper
	implements IvldItemMasterLocalService,
		ServiceWrapper<IvldItemMasterLocalService> {
	public IvldItemMasterLocalServiceWrapper(
		IvldItemMasterLocalService ivldItemMasterLocalService) {
		_ivldItemMasterLocalService = ivldItemMasterLocalService;
	}

	/**
	* Adds the ivld item master to the database. Also notifies the appropriate model listeners.
	*
	* @param ivldItemMaster the ivld item master
	* @return the ivld item master that was added
	*/
	@Override
	public com.stpl.app.parttwo.model.IvldItemMaster addIvldItemMaster(
		com.stpl.app.parttwo.model.IvldItemMaster ivldItemMaster) {
		return _ivldItemMasterLocalService.addIvldItemMaster(ivldItemMaster);
	}

	/**
	* Creates a new ivld item master with the primary key. Does not add the ivld item master to the database.
	*
	* @param ivldItemMasterSid the primary key for the new ivld item master
	* @return the new ivld item master
	*/
	@Override
	public com.stpl.app.parttwo.model.IvldItemMaster createIvldItemMaster(
		int ivldItemMasterSid) {
		return _ivldItemMasterLocalService.createIvldItemMaster(ivldItemMasterSid);
	}

	/**
	* Deletes the ivld item master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldItemMasterSid the primary key of the ivld item master
	* @return the ivld item master that was removed
	* @throws PortalException if a ivld item master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.IvldItemMaster deleteIvldItemMaster(
		int ivldItemMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldItemMasterLocalService.deleteIvldItemMaster(ivldItemMasterSid);
	}

	/**
	* Deletes the ivld item master from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldItemMaster the ivld item master
	* @return the ivld item master that was removed
	*/
	@Override
	public com.stpl.app.parttwo.model.IvldItemMaster deleteIvldItemMaster(
		com.stpl.app.parttwo.model.IvldItemMaster ivldItemMaster) {
		return _ivldItemMasterLocalService.deleteIvldItemMaster(ivldItemMaster);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldItemMasterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ivldItemMasterLocalService.dynamicQuery();
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
		return _ivldItemMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ivldItemMasterLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ivldItemMasterLocalService.dynamicQuery(dynamicQuery, start,
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
		return _ivldItemMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _ivldItemMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.parttwo.model.IvldItemMaster fetchIvldItemMaster(
		int ivldItemMasterSid) {
		return _ivldItemMasterLocalService.fetchIvldItemMaster(ivldItemMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _ivldItemMasterLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _ivldItemMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the ivld item master with the primary key.
	*
	* @param ivldItemMasterSid the primary key of the ivld item master
	* @return the ivld item master
	* @throws PortalException if a ivld item master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.IvldItemMaster getIvldItemMaster(
		int ivldItemMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldItemMasterLocalService.getIvldItemMaster(ivldItemMasterSid);
	}

	/**
	* Returns a range of all the ivld item masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld item masters
	* @param end the upper bound of the range of ivld item masters (not inclusive)
	* @return the range of ivld item masters
	*/
	@Override
	public java.util.List<com.stpl.app.parttwo.model.IvldItemMaster> getIvldItemMasters(
		int start, int end) {
		return _ivldItemMasterLocalService.getIvldItemMasters(start, end);
	}

	/**
	* Returns the number of ivld item masters.
	*
	* @return the number of ivld item masters
	*/
	@Override
	public int getIvldItemMastersCount() {
		return _ivldItemMasterLocalService.getIvldItemMastersCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ivldItemMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldItemMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the ivld item master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ivldItemMaster the ivld item master
	* @return the ivld item master that was updated
	*/
	@Override
	public com.stpl.app.parttwo.model.IvldItemMaster updateIvldItemMaster(
		com.stpl.app.parttwo.model.IvldItemMaster ivldItemMaster) {
		return _ivldItemMasterLocalService.updateIvldItemMaster(ivldItemMaster);
	}

	@Override
	public IvldItemMasterLocalService getWrappedService() {
		return _ivldItemMasterLocalService;
	}

	@Override
	public void setWrappedService(
		IvldItemMasterLocalService ivldItemMasterLocalService) {
		_ivldItemMasterLocalService = ivldItemMasterLocalService;
	}

	private IvldItemMasterLocalService _ivldItemMasterLocalService;
}