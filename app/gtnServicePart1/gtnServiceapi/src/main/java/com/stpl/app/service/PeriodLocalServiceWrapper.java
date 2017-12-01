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
 * Provides a wrapper for {@link PeriodLocalService}.
 *
 * @author
 * @see PeriodLocalService
 * @generated
 */
@ProviderType
public class PeriodLocalServiceWrapper implements PeriodLocalService,
	ServiceWrapper<PeriodLocalService> {
	public PeriodLocalServiceWrapper(PeriodLocalService periodLocalService) {
		_periodLocalService = periodLocalService;
	}

	/**
	* Adds the period to the database. Also notifies the appropriate model listeners.
	*
	* @param period the period
	* @return the period that was added
	*/
	@Override
	public com.stpl.app.model.Period addPeriod(com.stpl.app.model.Period period) {
		return _periodLocalService.addPeriod(period);
	}

	/**
	* Creates a new period with the primary key. Does not add the period to the database.
	*
	* @param periodSid the primary key for the new period
	* @return the new period
	*/
	@Override
	public com.stpl.app.model.Period createPeriod(int periodSid) {
		return _periodLocalService.createPeriod(periodSid);
	}

	/**
	* Deletes the period with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param periodSid the primary key of the period
	* @return the period that was removed
	* @throws PortalException if a period with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.Period deletePeriod(int periodSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _periodLocalService.deletePeriod(periodSid);
	}

	/**
	* Deletes the period from the database. Also notifies the appropriate model listeners.
	*
	* @param period the period
	* @return the period that was removed
	*/
	@Override
	public com.stpl.app.model.Period deletePeriod(
		com.stpl.app.model.Period period) {
		return _periodLocalService.deletePeriod(period);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _periodLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _periodLocalService.dynamicQuery();
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
		return _periodLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _periodLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _periodLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _periodLocalService.dynamicQueryCount(dynamicQuery);
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
		return _periodLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.stpl.app.model.Period fetchPeriod(int periodSid) {
		return _periodLocalService.fetchPeriod(periodSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _periodLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _periodLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _periodLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Returns the period with the primary key.
	*
	* @param periodSid the primary key of the period
	* @return the period
	* @throws PortalException if a period with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.Period getPeriod(int periodSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _periodLocalService.getPeriod(periodSid);
	}

	/**
	* Returns a range of all the periods.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of periods
	* @param end the upper bound of the range of periods (not inclusive)
	* @return the range of periods
	*/
	@Override
	public java.util.List<com.stpl.app.model.Period> getPeriods(int start,
		int end) {
		return _periodLocalService.getPeriods(start, end);
	}

	/**
	* Returns the number of periods.
	*
	* @return the number of periods
	*/
	@Override
	public int getPeriodsCount() {
		return _periodLocalService.getPeriodsCount();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _periodLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the period in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param period the period
	* @return the period that was updated
	*/
	@Override
	public com.stpl.app.model.Period updatePeriod(
		com.stpl.app.model.Period period) {
		return _periodLocalService.updatePeriod(period);
	}

	@Override
	public PeriodLocalService getWrappedService() {
		return _periodLocalService;
	}

	@Override
	public void setWrappedService(PeriodLocalService periodLocalService) {
		_periodLocalService = periodLocalService;
	}

	private PeriodLocalService _periodLocalService;
}