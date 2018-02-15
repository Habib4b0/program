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
 * Provides a wrapper for {@link SlaCalendarMasterLocalService}.
 *
 * @author
 * @see SlaCalendarMasterLocalService
 * @generated
 */
@ProviderType
public class SlaCalendarMasterLocalServiceWrapper
	implements SlaCalendarMasterLocalService,
		ServiceWrapper<SlaCalendarMasterLocalService> {
	public SlaCalendarMasterLocalServiceWrapper(
		SlaCalendarMasterLocalService slaCalendarMasterLocalService) {
		_slaCalendarMasterLocalService = slaCalendarMasterLocalService;
	}

	/**
	* Adds the sla calendar master to the database. Also notifies the appropriate model listeners.
	*
	* @param slaCalendarMaster the sla calendar master
	* @return the sla calendar master that was added
	*/
	@Override
	public com.stpl.app.parttwo.model.SlaCalendarMaster addSlaCalendarMaster(
		com.stpl.app.parttwo.model.SlaCalendarMaster slaCalendarMaster) {
		return _slaCalendarMasterLocalService.addSlaCalendarMaster(slaCalendarMaster);
	}

	/**
	* Creates a new sla calendar master with the primary key. Does not add the sla calendar master to the database.
	*
	* @param slaCalendarMasterSid the primary key for the new sla calendar master
	* @return the new sla calendar master
	*/
	@Override
	public com.stpl.app.parttwo.model.SlaCalendarMaster createSlaCalendarMaster(
		int slaCalendarMasterSid) {
		return _slaCalendarMasterLocalService.createSlaCalendarMaster(slaCalendarMasterSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _slaCalendarMasterLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the sla calendar master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param slaCalendarMasterSid the primary key of the sla calendar master
	* @return the sla calendar master that was removed
	* @throws PortalException if a sla calendar master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.SlaCalendarMaster deleteSlaCalendarMaster(
		int slaCalendarMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _slaCalendarMasterLocalService.deleteSlaCalendarMaster(slaCalendarMasterSid);
	}

	/**
	* Deletes the sla calendar master from the database. Also notifies the appropriate model listeners.
	*
	* @param slaCalendarMaster the sla calendar master
	* @return the sla calendar master that was removed
	*/
	@Override
	public com.stpl.app.parttwo.model.SlaCalendarMaster deleteSlaCalendarMaster(
		com.stpl.app.parttwo.model.SlaCalendarMaster slaCalendarMaster) {
		return _slaCalendarMasterLocalService.deleteSlaCalendarMaster(slaCalendarMaster);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _slaCalendarMasterLocalService.dynamicQuery();
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
		return _slaCalendarMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.SlaCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _slaCalendarMasterLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.SlaCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _slaCalendarMasterLocalService.dynamicQuery(dynamicQuery, start,
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
		return _slaCalendarMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _slaCalendarMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.parttwo.model.SlaCalendarMaster fetchSlaCalendarMaster(
		int slaCalendarMasterSid) {
		return _slaCalendarMasterLocalService.fetchSlaCalendarMaster(slaCalendarMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _slaCalendarMasterLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _slaCalendarMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _slaCalendarMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _slaCalendarMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the sla calendar master with the primary key.
	*
	* @param slaCalendarMasterSid the primary key of the sla calendar master
	* @return the sla calendar master
	* @throws PortalException if a sla calendar master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.SlaCalendarMaster getSlaCalendarMaster(
		int slaCalendarMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _slaCalendarMasterLocalService.getSlaCalendarMaster(slaCalendarMasterSid);
	}

	/**
	* Returns a range of all the sla calendar masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.SlaCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sla calendar masters
	* @param end the upper bound of the range of sla calendar masters (not inclusive)
	* @return the range of sla calendar masters
	*/
	@Override
	public java.util.List<com.stpl.app.parttwo.model.SlaCalendarMaster> getSlaCalendarMasters(
		int start, int end) {
		return _slaCalendarMasterLocalService.getSlaCalendarMasters(start, end);
	}

	/**
	* Returns the number of sla calendar masters.
	*
	* @return the number of sla calendar masters
	*/
	@Override
	public int getSlaCalendarMastersCount() {
		return _slaCalendarMasterLocalService.getSlaCalendarMastersCount();
	}

	/**
	* Updates the sla calendar master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param slaCalendarMaster the sla calendar master
	* @return the sla calendar master that was updated
	*/
	@Override
	public com.stpl.app.parttwo.model.SlaCalendarMaster updateSlaCalendarMaster(
		com.stpl.app.parttwo.model.SlaCalendarMaster slaCalendarMaster) {
		return _slaCalendarMasterLocalService.updateSlaCalendarMaster(slaCalendarMaster);
	}

	@Override
	public SlaCalendarMasterLocalService getWrappedService() {
		return _slaCalendarMasterLocalService;
	}

	@Override
	public void setWrappedService(
		SlaCalendarMasterLocalService slaCalendarMasterLocalService) {
		_slaCalendarMasterLocalService = slaCalendarMasterLocalService;
	}

	private SlaCalendarMasterLocalService _slaCalendarMasterLocalService;
}