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
 * Provides a wrapper for {@link SlaCalendarDetailsLocalService}.
 *
 * @author
 * @see SlaCalendarDetailsLocalService
 * @generated
 */
@ProviderType
public class SlaCalendarDetailsLocalServiceWrapper
	implements SlaCalendarDetailsLocalService,
		ServiceWrapper<SlaCalendarDetailsLocalService> {
	public SlaCalendarDetailsLocalServiceWrapper(
		SlaCalendarDetailsLocalService slaCalendarDetailsLocalService) {
		_slaCalendarDetailsLocalService = slaCalendarDetailsLocalService;
	}

	/**
	* Adds the sla calendar details to the database. Also notifies the appropriate model listeners.
	*
	* @param slaCalendarDetails the sla calendar details
	* @return the sla calendar details that was added
	*/
	@Override
	public com.stpl.app.parttwo.model.SlaCalendarDetails addSlaCalendarDetails(
		com.stpl.app.parttwo.model.SlaCalendarDetails slaCalendarDetails) {
		return _slaCalendarDetailsLocalService.addSlaCalendarDetails(slaCalendarDetails);
	}

	/**
	* Creates a new sla calendar details with the primary key. Does not add the sla calendar details to the database.
	*
	* @param slaCalendarDetailsSid the primary key for the new sla calendar details
	* @return the new sla calendar details
	*/
	@Override
	public com.stpl.app.parttwo.model.SlaCalendarDetails createSlaCalendarDetails(
		int slaCalendarDetailsSid) {
		return _slaCalendarDetailsLocalService.createSlaCalendarDetails(slaCalendarDetailsSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _slaCalendarDetailsLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the sla calendar details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param slaCalendarDetailsSid the primary key of the sla calendar details
	* @return the sla calendar details that was removed
	* @throws PortalException if a sla calendar details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.SlaCalendarDetails deleteSlaCalendarDetails(
		int slaCalendarDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _slaCalendarDetailsLocalService.deleteSlaCalendarDetails(slaCalendarDetailsSid);
	}

	/**
	* Deletes the sla calendar details from the database. Also notifies the appropriate model listeners.
	*
	* @param slaCalendarDetails the sla calendar details
	* @return the sla calendar details that was removed
	*/
	@Override
	public com.stpl.app.parttwo.model.SlaCalendarDetails deleteSlaCalendarDetails(
		com.stpl.app.parttwo.model.SlaCalendarDetails slaCalendarDetails) {
		return _slaCalendarDetailsLocalService.deleteSlaCalendarDetails(slaCalendarDetails);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _slaCalendarDetailsLocalService.dynamicQuery();
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
		return _slaCalendarDetailsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.SlaCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _slaCalendarDetailsLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.SlaCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _slaCalendarDetailsLocalService.dynamicQuery(dynamicQuery,
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
		return _slaCalendarDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _slaCalendarDetailsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.parttwo.model.SlaCalendarDetails fetchSlaCalendarDetails(
		int slaCalendarDetailsSid) {
		return _slaCalendarDetailsLocalService.fetchSlaCalendarDetails(slaCalendarDetailsSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _slaCalendarDetailsLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _slaCalendarDetailsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _slaCalendarDetailsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _slaCalendarDetailsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the sla calendar details with the primary key.
	*
	* @param slaCalendarDetailsSid the primary key of the sla calendar details
	* @return the sla calendar details
	* @throws PortalException if a sla calendar details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.SlaCalendarDetails getSlaCalendarDetails(
		int slaCalendarDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _slaCalendarDetailsLocalService.getSlaCalendarDetails(slaCalendarDetailsSid);
	}

	/**
	* Returns a range of all the sla calendar detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.SlaCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sla calendar detailses
	* @param end the upper bound of the range of sla calendar detailses (not inclusive)
	* @return the range of sla calendar detailses
	*/
	@Override
	public java.util.List<com.stpl.app.parttwo.model.SlaCalendarDetails> getSlaCalendarDetailses(
		int start, int end) {
		return _slaCalendarDetailsLocalService.getSlaCalendarDetailses(start,
			end);
	}

	/**
	* Returns the number of sla calendar detailses.
	*
	* @return the number of sla calendar detailses
	*/
	@Override
	public int getSlaCalendarDetailsesCount() {
		return _slaCalendarDetailsLocalService.getSlaCalendarDetailsesCount();
	}

	/**
	* Updates the sla calendar details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param slaCalendarDetails the sla calendar details
	* @return the sla calendar details that was updated
	*/
	@Override
	public com.stpl.app.parttwo.model.SlaCalendarDetails updateSlaCalendarDetails(
		com.stpl.app.parttwo.model.SlaCalendarDetails slaCalendarDetails) {
		return _slaCalendarDetailsLocalService.updateSlaCalendarDetails(slaCalendarDetails);
	}

	@Override
	public SlaCalendarDetailsLocalService getWrappedService() {
		return _slaCalendarDetailsLocalService;
	}

	@Override
	public void setWrappedService(
		SlaCalendarDetailsLocalService slaCalendarDetailsLocalService) {
		_slaCalendarDetailsLocalService = slaCalendarDetailsLocalService;
	}

	private SlaCalendarDetailsLocalService _slaCalendarDetailsLocalService;
}