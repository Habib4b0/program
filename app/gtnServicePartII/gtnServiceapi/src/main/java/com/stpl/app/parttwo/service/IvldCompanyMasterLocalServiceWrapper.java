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
 * Provides a wrapper for {@link IvldCompanyMasterLocalService}.
 *
 * @author
 * @see IvldCompanyMasterLocalService
 * @generated
 */
@ProviderType
public class IvldCompanyMasterLocalServiceWrapper
	implements IvldCompanyMasterLocalService,
		ServiceWrapper<IvldCompanyMasterLocalService> {
	public IvldCompanyMasterLocalServiceWrapper(
		IvldCompanyMasterLocalService ivldCompanyMasterLocalService) {
		_ivldCompanyMasterLocalService = ivldCompanyMasterLocalService;
	}

	/**
	* Adds the ivld company master to the database. Also notifies the appropriate model listeners.
	*
	* @param ivldCompanyMaster the ivld company master
	* @return the ivld company master that was added
	*/
	@Override
	public com.stpl.app.parttwo.model.IvldCompanyMaster addIvldCompanyMaster(
		com.stpl.app.parttwo.model.IvldCompanyMaster ivldCompanyMaster) {
		return _ivldCompanyMasterLocalService.addIvldCompanyMaster(ivldCompanyMaster);
	}

	/**
	* Creates a new ivld company master with the primary key. Does not add the ivld company master to the database.
	*
	* @param ivldCompanyMasterSid the primary key for the new ivld company master
	* @return the new ivld company master
	*/
	@Override
	public com.stpl.app.parttwo.model.IvldCompanyMaster createIvldCompanyMaster(
		int ivldCompanyMasterSid) {
		return _ivldCompanyMasterLocalService.createIvldCompanyMaster(ivldCompanyMasterSid);
	}

	/**
	* Deletes the ivld company master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldCompanyMasterSid the primary key of the ivld company master
	* @return the ivld company master that was removed
	* @throws PortalException if a ivld company master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.IvldCompanyMaster deleteIvldCompanyMaster(
		int ivldCompanyMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldCompanyMasterLocalService.deleteIvldCompanyMaster(ivldCompanyMasterSid);
	}

	/**
	* Deletes the ivld company master from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldCompanyMaster the ivld company master
	* @return the ivld company master that was removed
	*/
	@Override
	public com.stpl.app.parttwo.model.IvldCompanyMaster deleteIvldCompanyMaster(
		com.stpl.app.parttwo.model.IvldCompanyMaster ivldCompanyMaster) {
		return _ivldCompanyMasterLocalService.deleteIvldCompanyMaster(ivldCompanyMaster);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldCompanyMasterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ivldCompanyMasterLocalService.dynamicQuery();
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
		return _ivldCompanyMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ivldCompanyMasterLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ivldCompanyMasterLocalService.dynamicQuery(dynamicQuery, start,
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
		return _ivldCompanyMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _ivldCompanyMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.parttwo.model.IvldCompanyMaster fetchIvldCompanyMaster(
		int ivldCompanyMasterSid) {
		return _ivldCompanyMasterLocalService.fetchIvldCompanyMaster(ivldCompanyMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _ivldCompanyMasterLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _ivldCompanyMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the ivld company master with the primary key.
	*
	* @param ivldCompanyMasterSid the primary key of the ivld company master
	* @return the ivld company master
	* @throws PortalException if a ivld company master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.IvldCompanyMaster getIvldCompanyMaster(
		int ivldCompanyMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldCompanyMasterLocalService.getIvldCompanyMaster(ivldCompanyMasterSid);
	}

	/**
	* Returns a range of all the ivld company masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld company masters
	* @param end the upper bound of the range of ivld company masters (not inclusive)
	* @return the range of ivld company masters
	*/
	@Override
	public java.util.List<com.stpl.app.parttwo.model.IvldCompanyMaster> getIvldCompanyMasters(
		int start, int end) {
		return _ivldCompanyMasterLocalService.getIvldCompanyMasters(start, end);
	}

	/**
	* Returns the number of ivld company masters.
	*
	* @return the number of ivld company masters
	*/
	@Override
	public int getIvldCompanyMastersCount() {
		return _ivldCompanyMasterLocalService.getIvldCompanyMastersCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ivldCompanyMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldCompanyMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the ivld company master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ivldCompanyMaster the ivld company master
	* @return the ivld company master that was updated
	*/
	@Override
	public com.stpl.app.parttwo.model.IvldCompanyMaster updateIvldCompanyMaster(
		com.stpl.app.parttwo.model.IvldCompanyMaster ivldCompanyMaster) {
		return _ivldCompanyMasterLocalService.updateIvldCompanyMaster(ivldCompanyMaster);
	}

	@Override
	public IvldCompanyMasterLocalService getWrappedService() {
		return _ivldCompanyMasterLocalService;
	}

	@Override
	public void setWrappedService(
		IvldCompanyMasterLocalService ivldCompanyMasterLocalService) {
		_ivldCompanyMasterLocalService = ivldCompanyMasterLocalService;
	}

	private IvldCompanyMasterLocalService _ivldCompanyMasterLocalService;
}