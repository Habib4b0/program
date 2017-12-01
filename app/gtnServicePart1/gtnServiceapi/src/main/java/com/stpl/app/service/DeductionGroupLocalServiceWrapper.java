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
 * Provides a wrapper for {@link DeductionGroupLocalService}.
 *
 * @author
 * @see DeductionGroupLocalService
 * @generated
 */
@ProviderType
public class DeductionGroupLocalServiceWrapper
	implements DeductionGroupLocalService,
		ServiceWrapper<DeductionGroupLocalService> {
	public DeductionGroupLocalServiceWrapper(
		DeductionGroupLocalService deductionGroupLocalService) {
		_deductionGroupLocalService = deductionGroupLocalService;
	}

	/**
	* Adds the deduction group to the database. Also notifies the appropriate model listeners.
	*
	* @param deductionGroup the deduction group
	* @return the deduction group that was added
	*/
	@Override
	public com.stpl.app.model.DeductionGroup addDeductionGroup(
		com.stpl.app.model.DeductionGroup deductionGroup) {
		return _deductionGroupLocalService.addDeductionGroup(deductionGroup);
	}

	/**
	* Creates a new deduction group with the primary key. Does not add the deduction group to the database.
	*
	* @param deductionGroupSid the primary key for the new deduction group
	* @return the new deduction group
	*/
	@Override
	public com.stpl.app.model.DeductionGroup createDeductionGroup(
		int deductionGroupSid) {
		return _deductionGroupLocalService.createDeductionGroup(deductionGroupSid);
	}

	/**
	* Deletes the deduction group from the database. Also notifies the appropriate model listeners.
	*
	* @param deductionGroup the deduction group
	* @return the deduction group that was removed
	*/
	@Override
	public com.stpl.app.model.DeductionGroup deleteDeductionGroup(
		com.stpl.app.model.DeductionGroup deductionGroup) {
		return _deductionGroupLocalService.deleteDeductionGroup(deductionGroup);
	}

	/**
	* Deletes the deduction group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param deductionGroupSid the primary key of the deduction group
	* @return the deduction group that was removed
	* @throws PortalException if a deduction group with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.DeductionGroup deleteDeductionGroup(
		int deductionGroupSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _deductionGroupLocalService.deleteDeductionGroup(deductionGroupSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _deductionGroupLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _deductionGroupLocalService.dynamicQuery();
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
		return _deductionGroupLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _deductionGroupLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _deductionGroupLocalService.dynamicQuery(dynamicQuery, start,
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
		return _deductionGroupLocalService.dynamicQueryCount(dynamicQuery);
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
		return _deductionGroupLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.DeductionGroup fetchDeductionGroup(
		int deductionGroupSid) {
		return _deductionGroupLocalService.fetchDeductionGroup(deductionGroupSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _deductionGroupLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the deduction group with the primary key.
	*
	* @param deductionGroupSid the primary key of the deduction group
	* @return the deduction group
	* @throws PortalException if a deduction group with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.DeductionGroup getDeductionGroup(
		int deductionGroupSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _deductionGroupLocalService.getDeductionGroup(deductionGroupSid);
	}

	/**
	* Returns a range of all the deduction groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deduction groups
	* @param end the upper bound of the range of deduction groups (not inclusive)
	* @return the range of deduction groups
	*/
	@Override
	public java.util.List<com.stpl.app.model.DeductionGroup> getDeductionGroups(
		int start, int end) {
		return _deductionGroupLocalService.getDeductionGroups(start, end);
	}

	/**
	* Returns the number of deduction groups.
	*
	* @return the number of deduction groups
	*/
	@Override
	public int getDeductionGroupsCount() {
		return _deductionGroupLocalService.getDeductionGroupsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _deductionGroupLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _deductionGroupLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _deductionGroupLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the deduction group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param deductionGroup the deduction group
	* @return the deduction group that was updated
	*/
	@Override
	public com.stpl.app.model.DeductionGroup updateDeductionGroup(
		com.stpl.app.model.DeductionGroup deductionGroup) {
		return _deductionGroupLocalService.updateDeductionGroup(deductionGroup);
	}

	@Override
	public DeductionGroupLocalService getWrappedService() {
		return _deductionGroupLocalService;
	}

	@Override
	public void setWrappedService(
		DeductionGroupLocalService deductionGroupLocalService) {
		_deductionGroupLocalService = deductionGroupLocalService;
	}

	private DeductionGroupLocalService _deductionGroupLocalService;
}