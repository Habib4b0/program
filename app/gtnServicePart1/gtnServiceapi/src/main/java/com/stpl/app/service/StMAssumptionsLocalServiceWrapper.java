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
 * Provides a wrapper for {@link StMAssumptionsLocalService}.
 *
 * @author
 * @see StMAssumptionsLocalService
 * @generated
 */
@ProviderType
public class StMAssumptionsLocalServiceWrapper
	implements StMAssumptionsLocalService,
		ServiceWrapper<StMAssumptionsLocalService> {
	public StMAssumptionsLocalServiceWrapper(
		StMAssumptionsLocalService stMAssumptionsLocalService) {
		_stMAssumptionsLocalService = stMAssumptionsLocalService;
	}

	/**
	* Adds the st m assumptions to the database. Also notifies the appropriate model listeners.
	*
	* @param stMAssumptions the st m assumptions
	* @return the st m assumptions that was added
	*/
	@Override
	public com.stpl.app.model.StMAssumptions addStMAssumptions(
		com.stpl.app.model.StMAssumptions stMAssumptions) {
		return _stMAssumptionsLocalService.addStMAssumptions(stMAssumptions);
	}

	/**
	* Creates a new st m assumptions with the primary key. Does not add the st m assumptions to the database.
	*
	* @param stMAssumptionsPK the primary key for the new st m assumptions
	* @return the new st m assumptions
	*/
	@Override
	public com.stpl.app.model.StMAssumptions createStMAssumptions(
		com.stpl.app.service.persistence.StMAssumptionsPK stMAssumptionsPK) {
		return _stMAssumptionsLocalService.createStMAssumptions(stMAssumptionsPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stMAssumptionsLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the st m assumptions from the database. Also notifies the appropriate model listeners.
	*
	* @param stMAssumptions the st m assumptions
	* @return the st m assumptions that was removed
	*/
	@Override
	public com.stpl.app.model.StMAssumptions deleteStMAssumptions(
		com.stpl.app.model.StMAssumptions stMAssumptions) {
		return _stMAssumptionsLocalService.deleteStMAssumptions(stMAssumptions);
	}

	/**
	* Deletes the st m assumptions with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stMAssumptionsPK the primary key of the st m assumptions
	* @return the st m assumptions that was removed
	* @throws PortalException if a st m assumptions with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.StMAssumptions deleteStMAssumptions(
		com.stpl.app.service.persistence.StMAssumptionsPK stMAssumptionsPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stMAssumptionsLocalService.deleteStMAssumptions(stMAssumptionsPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _stMAssumptionsLocalService.dynamicQuery();
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
		return _stMAssumptionsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StMAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stMAssumptionsLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StMAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stMAssumptionsLocalService.dynamicQuery(dynamicQuery, start,
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
		return _stMAssumptionsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _stMAssumptionsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.StMAssumptions fetchStMAssumptions(
		com.stpl.app.service.persistence.StMAssumptionsPK stMAssumptionsPK) {
		return _stMAssumptionsLocalService.fetchStMAssumptions(stMAssumptionsPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _stMAssumptionsLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _stMAssumptionsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _stMAssumptionsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stMAssumptionsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the st m assumptions with the primary key.
	*
	* @param stMAssumptionsPK the primary key of the st m assumptions
	* @return the st m assumptions
	* @throws PortalException if a st m assumptions with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.StMAssumptions getStMAssumptions(
		com.stpl.app.service.persistence.StMAssumptionsPK stMAssumptionsPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stMAssumptionsLocalService.getStMAssumptions(stMAssumptionsPK);
	}

	/**
	* Returns a range of all the st m assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StMAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st m assumptionses
	* @param end the upper bound of the range of st m assumptionses (not inclusive)
	* @return the range of st m assumptionses
	*/
	@Override
	public java.util.List<com.stpl.app.model.StMAssumptions> getStMAssumptionses(
		int start, int end) {
		return _stMAssumptionsLocalService.getStMAssumptionses(start, end);
	}

	/**
	* Returns the number of st m assumptionses.
	*
	* @return the number of st m assumptionses
	*/
	@Override
	public int getStMAssumptionsesCount() {
		return _stMAssumptionsLocalService.getStMAssumptionsesCount();
	}

	/**
	* Updates the st m assumptions in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param stMAssumptions the st m assumptions
	* @return the st m assumptions that was updated
	*/
	@Override
	public com.stpl.app.model.StMAssumptions updateStMAssumptions(
		com.stpl.app.model.StMAssumptions stMAssumptions) {
		return _stMAssumptionsLocalService.updateStMAssumptions(stMAssumptions);
	}

	@Override
	public StMAssumptionsLocalService getWrappedService() {
		return _stMAssumptionsLocalService;
	}

	@Override
	public void setWrappedService(
		StMAssumptionsLocalService stMAssumptionsLocalService) {
		_stMAssumptionsLocalService = stMAssumptionsLocalService;
	}

	private StMAssumptionsLocalService _stMAssumptionsLocalService;
}