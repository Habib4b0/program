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
 * Provides a wrapper for {@link StNmAssumptionsLocalService}.
 *
 * @author
 * @see StNmAssumptionsLocalService
 * @generated
 */
@ProviderType
public class StNmAssumptionsLocalServiceWrapper
	implements StNmAssumptionsLocalService,
		ServiceWrapper<StNmAssumptionsLocalService> {
	public StNmAssumptionsLocalServiceWrapper(
		StNmAssumptionsLocalService stNmAssumptionsLocalService) {
		_stNmAssumptionsLocalService = stNmAssumptionsLocalService;
	}

	/**
	* Adds the st nm assumptions to the database. Also notifies the appropriate model listeners.
	*
	* @param stNmAssumptions the st nm assumptions
	* @return the st nm assumptions that was added
	*/
	@Override
	public com.stpl.app.model.StNmAssumptions addStNmAssumptions(
		com.stpl.app.model.StNmAssumptions stNmAssumptions) {
		return _stNmAssumptionsLocalService.addStNmAssumptions(stNmAssumptions);
	}

	/**
	* Creates a new st nm assumptions with the primary key. Does not add the st nm assumptions to the database.
	*
	* @param stNmAssumptionsPK the primary key for the new st nm assumptions
	* @return the new st nm assumptions
	*/
	@Override
	public com.stpl.app.model.StNmAssumptions createStNmAssumptions(
		com.stpl.app.service.persistence.StNmAssumptionsPK stNmAssumptionsPK) {
		return _stNmAssumptionsLocalService.createStNmAssumptions(stNmAssumptionsPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stNmAssumptionsLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the st nm assumptions from the database. Also notifies the appropriate model listeners.
	*
	* @param stNmAssumptions the st nm assumptions
	* @return the st nm assumptions that was removed
	*/
	@Override
	public com.stpl.app.model.StNmAssumptions deleteStNmAssumptions(
		com.stpl.app.model.StNmAssumptions stNmAssumptions) {
		return _stNmAssumptionsLocalService.deleteStNmAssumptions(stNmAssumptions);
	}

	/**
	* Deletes the st nm assumptions with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stNmAssumptionsPK the primary key of the st nm assumptions
	* @return the st nm assumptions that was removed
	* @throws PortalException if a st nm assumptions with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.StNmAssumptions deleteStNmAssumptions(
		com.stpl.app.service.persistence.StNmAssumptionsPK stNmAssumptionsPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stNmAssumptionsLocalService.deleteStNmAssumptions(stNmAssumptionsPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _stNmAssumptionsLocalService.dynamicQuery();
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
		return _stNmAssumptionsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stNmAssumptionsLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stNmAssumptionsLocalService.dynamicQuery(dynamicQuery, start,
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
		return _stNmAssumptionsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _stNmAssumptionsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.StNmAssumptions fetchStNmAssumptions(
		com.stpl.app.service.persistence.StNmAssumptionsPK stNmAssumptionsPK) {
		return _stNmAssumptionsLocalService.fetchStNmAssumptions(stNmAssumptionsPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _stNmAssumptionsLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _stNmAssumptionsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _stNmAssumptionsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stNmAssumptionsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the st nm assumptions with the primary key.
	*
	* @param stNmAssumptionsPK the primary key of the st nm assumptions
	* @return the st nm assumptions
	* @throws PortalException if a st nm assumptions with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.StNmAssumptions getStNmAssumptions(
		com.stpl.app.service.persistence.StNmAssumptionsPK stNmAssumptionsPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stNmAssumptionsLocalService.getStNmAssumptions(stNmAssumptionsPK);
	}

	/**
	* Returns a range of all the st nm assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm assumptionses
	* @param end the upper bound of the range of st nm assumptionses (not inclusive)
	* @return the range of st nm assumptionses
	*/
	@Override
	public java.util.List<com.stpl.app.model.StNmAssumptions> getStNmAssumptionses(
		int start, int end) {
		return _stNmAssumptionsLocalService.getStNmAssumptionses(start, end);
	}

	/**
	* Returns the number of st nm assumptionses.
	*
	* @return the number of st nm assumptionses
	*/
	@Override
	public int getStNmAssumptionsesCount() {
		return _stNmAssumptionsLocalService.getStNmAssumptionsesCount();
	}

	/**
	* Updates the st nm assumptions in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param stNmAssumptions the st nm assumptions
	* @return the st nm assumptions that was updated
	*/
	@Override
	public com.stpl.app.model.StNmAssumptions updateStNmAssumptions(
		com.stpl.app.model.StNmAssumptions stNmAssumptions) {
		return _stNmAssumptionsLocalService.updateStNmAssumptions(stNmAssumptions);
	}

	@Override
	public StNmAssumptionsLocalService getWrappedService() {
		return _stNmAssumptionsLocalService;
	}

	@Override
	public void setWrappedService(
		StNmAssumptionsLocalService stNmAssumptionsLocalService) {
		_stNmAssumptionsLocalService = stNmAssumptionsLocalService;
	}

	private StNmAssumptionsLocalService _stNmAssumptionsLocalService;
}