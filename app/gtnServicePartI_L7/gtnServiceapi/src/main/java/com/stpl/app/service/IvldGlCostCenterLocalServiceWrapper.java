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
 * Provides a wrapper for {@link IvldGlCostCenterLocalService}.
 *
 * @author
 * @see IvldGlCostCenterLocalService
 * @generated
 */
@ProviderType
public class IvldGlCostCenterLocalServiceWrapper
	implements IvldGlCostCenterLocalService,
		ServiceWrapper<IvldGlCostCenterLocalService> {
	public IvldGlCostCenterLocalServiceWrapper(
		IvldGlCostCenterLocalService ivldGlCostCenterLocalService) {
		_ivldGlCostCenterLocalService = ivldGlCostCenterLocalService;
	}

	/**
	* Adds the ivld gl cost center to the database. Also notifies the appropriate model listeners.
	*
	* @param ivldGlCostCenter the ivld gl cost center
	* @return the ivld gl cost center that was added
	*/
	@Override
	public com.stpl.app.model.IvldGlCostCenter addIvldGlCostCenter(
		com.stpl.app.model.IvldGlCostCenter ivldGlCostCenter) {
		return _ivldGlCostCenterLocalService.addIvldGlCostCenter(ivldGlCostCenter);
	}

	/**
	* Creates a new ivld gl cost center with the primary key. Does not add the ivld gl cost center to the database.
	*
	* @param ivldGlCostCenterSid the primary key for the new ivld gl cost center
	* @return the new ivld gl cost center
	*/
	@Override
	public com.stpl.app.model.IvldGlCostCenter createIvldGlCostCenter(
		int ivldGlCostCenterSid) {
		return _ivldGlCostCenterLocalService.createIvldGlCostCenter(ivldGlCostCenterSid);
	}

	/**
	* Deletes the ivld gl cost center with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldGlCostCenterSid the primary key of the ivld gl cost center
	* @return the ivld gl cost center that was removed
	* @throws PortalException if a ivld gl cost center with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.IvldGlCostCenter deleteIvldGlCostCenter(
		int ivldGlCostCenterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldGlCostCenterLocalService.deleteIvldGlCostCenter(ivldGlCostCenterSid);
	}

	/**
	* Deletes the ivld gl cost center from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldGlCostCenter the ivld gl cost center
	* @return the ivld gl cost center that was removed
	*/
	@Override
	public com.stpl.app.model.IvldGlCostCenter deleteIvldGlCostCenter(
		com.stpl.app.model.IvldGlCostCenter ivldGlCostCenter) {
		return _ivldGlCostCenterLocalService.deleteIvldGlCostCenter(ivldGlCostCenter);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldGlCostCenterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ivldGlCostCenterLocalService.dynamicQuery();
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
		return _ivldGlCostCenterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldGlCostCenterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ivldGlCostCenterLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldGlCostCenterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ivldGlCostCenterLocalService.dynamicQuery(dynamicQuery, start,
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
		return _ivldGlCostCenterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _ivldGlCostCenterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.IvldGlCostCenter fetchIvldGlCostCenter(
		int ivldGlCostCenterSid) {
		return _ivldGlCostCenterLocalService.fetchIvldGlCostCenter(ivldGlCostCenterSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _ivldGlCostCenterLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _ivldGlCostCenterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the ivld gl cost center with the primary key.
	*
	* @param ivldGlCostCenterSid the primary key of the ivld gl cost center
	* @return the ivld gl cost center
	* @throws PortalException if a ivld gl cost center with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.IvldGlCostCenter getIvldGlCostCenter(
		int ivldGlCostCenterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldGlCostCenterLocalService.getIvldGlCostCenter(ivldGlCostCenterSid);
	}

	/**
	* Returns a range of all the ivld gl cost centers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldGlCostCenterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld gl cost centers
	* @param end the upper bound of the range of ivld gl cost centers (not inclusive)
	* @return the range of ivld gl cost centers
	*/
	@Override
	public java.util.List<com.stpl.app.model.IvldGlCostCenter> getIvldGlCostCenters(
		int start, int end) {
		return _ivldGlCostCenterLocalService.getIvldGlCostCenters(start, end);
	}

	/**
	* Returns the number of ivld gl cost centers.
	*
	* @return the number of ivld gl cost centers
	*/
	@Override
	public int getIvldGlCostCentersCount() {
		return _ivldGlCostCenterLocalService.getIvldGlCostCentersCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ivldGlCostCenterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldGlCostCenterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the ivld gl cost center in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ivldGlCostCenter the ivld gl cost center
	* @return the ivld gl cost center that was updated
	*/
	@Override
	public com.stpl.app.model.IvldGlCostCenter updateIvldGlCostCenter(
		com.stpl.app.model.IvldGlCostCenter ivldGlCostCenter) {
		return _ivldGlCostCenterLocalService.updateIvldGlCostCenter(ivldGlCostCenter);
	}

	@Override
	public IvldGlCostCenterLocalService getWrappedService() {
		return _ivldGlCostCenterLocalService;
	}

	@Override
	public void setWrappedService(
		IvldGlCostCenterLocalService ivldGlCostCenterLocalService) {
		_ivldGlCostCenterLocalService = ivldGlCostCenterLocalService;
	}

	private IvldGlCostCenterLocalService _ivldGlCostCenterLocalService;
}