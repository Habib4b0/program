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
 * Provides a wrapper for {@link DeductionCalendarMasterLocalService}.
 *
 * @author
 * @see DeductionCalendarMasterLocalService
 * @generated
 */
@ProviderType
public class DeductionCalendarMasterLocalServiceWrapper
	implements DeductionCalendarMasterLocalService,
		ServiceWrapper<DeductionCalendarMasterLocalService> {
	public DeductionCalendarMasterLocalServiceWrapper(
		DeductionCalendarMasterLocalService deductionCalendarMasterLocalService) {
		_deductionCalendarMasterLocalService = deductionCalendarMasterLocalService;
	}

	/**
	* Adds the deduction calendar master to the database. Also notifies the appropriate model listeners.
	*
	* @param deductionCalendarMaster the deduction calendar master
	* @return the deduction calendar master that was added
	*/
	@Override
	public com.stpl.app.model.DeductionCalendarMaster addDeductionCalendarMaster(
		com.stpl.app.model.DeductionCalendarMaster deductionCalendarMaster) {
		return _deductionCalendarMasterLocalService.addDeductionCalendarMaster(deductionCalendarMaster);
	}

	/**
	* Creates a new deduction calendar master with the primary key. Does not add the deduction calendar master to the database.
	*
	* @param deductionCalendarMasterSid the primary key for the new deduction calendar master
	* @return the new deduction calendar master
	*/
	@Override
	public com.stpl.app.model.DeductionCalendarMaster createDeductionCalendarMaster(
		int deductionCalendarMasterSid) {
		return _deductionCalendarMasterLocalService.createDeductionCalendarMaster(deductionCalendarMasterSid);
	}

	/**
	* Deletes the deduction calendar master from the database. Also notifies the appropriate model listeners.
	*
	* @param deductionCalendarMaster the deduction calendar master
	* @return the deduction calendar master that was removed
	*/
	@Override
	public com.stpl.app.model.DeductionCalendarMaster deleteDeductionCalendarMaster(
		com.stpl.app.model.DeductionCalendarMaster deductionCalendarMaster) {
		return _deductionCalendarMasterLocalService.deleteDeductionCalendarMaster(deductionCalendarMaster);
	}

	/**
	* Deletes the deduction calendar master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param deductionCalendarMasterSid the primary key of the deduction calendar master
	* @return the deduction calendar master that was removed
	* @throws PortalException if a deduction calendar master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.DeductionCalendarMaster deleteDeductionCalendarMaster(
		int deductionCalendarMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _deductionCalendarMasterLocalService.deleteDeductionCalendarMaster(deductionCalendarMasterSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _deductionCalendarMasterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _deductionCalendarMasterLocalService.dynamicQuery();
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
		return _deductionCalendarMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _deductionCalendarMasterLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _deductionCalendarMasterLocalService.dynamicQuery(dynamicQuery,
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
		return _deductionCalendarMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _deductionCalendarMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.DeductionCalendarMaster fetchDeductionCalendarMaster(
		int deductionCalendarMasterSid) {
		return _deductionCalendarMasterLocalService.fetchDeductionCalendarMaster(deductionCalendarMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _deductionCalendarMasterLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the deduction calendar master with the primary key.
	*
	* @param deductionCalendarMasterSid the primary key of the deduction calendar master
	* @return the deduction calendar master
	* @throws PortalException if a deduction calendar master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.DeductionCalendarMaster getDeductionCalendarMaster(
		int deductionCalendarMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _deductionCalendarMasterLocalService.getDeductionCalendarMaster(deductionCalendarMasterSid);
	}

	/**
	* Returns a range of all the deduction calendar masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deduction calendar masters
	* @param end the upper bound of the range of deduction calendar masters (not inclusive)
	* @return the range of deduction calendar masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.DeductionCalendarMaster> getDeductionCalendarMasters(
		int start, int end) {
		return _deductionCalendarMasterLocalService.getDeductionCalendarMasters(start,
			end);
	}

	/**
	* Returns the number of deduction calendar masters.
	*
	* @return the number of deduction calendar masters
	*/
	@Override
	public int getDeductionCalendarMastersCount() {
		return _deductionCalendarMasterLocalService.getDeductionCalendarMastersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _deductionCalendarMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _deductionCalendarMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _deductionCalendarMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the deduction calendar master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param deductionCalendarMaster the deduction calendar master
	* @return the deduction calendar master that was updated
	*/
	@Override
	public com.stpl.app.model.DeductionCalendarMaster updateDeductionCalendarMaster(
		com.stpl.app.model.DeductionCalendarMaster deductionCalendarMaster) {
		return _deductionCalendarMasterLocalService.updateDeductionCalendarMaster(deductionCalendarMaster);
	}

	@Override
	public DeductionCalendarMasterLocalService getWrappedService() {
		return _deductionCalendarMasterLocalService;
	}

	@Override
	public void setWrappedService(
		DeductionCalendarMasterLocalService deductionCalendarMasterLocalService) {
		_deductionCalendarMasterLocalService = deductionCalendarMasterLocalService;
	}

	private DeductionCalendarMasterLocalService _deductionCalendarMasterLocalService;
}