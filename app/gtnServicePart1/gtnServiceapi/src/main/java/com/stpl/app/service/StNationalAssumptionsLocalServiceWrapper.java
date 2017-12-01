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
 * Provides a wrapper for {@link StNationalAssumptionsLocalService}.
 *
 * @author
 * @see StNationalAssumptionsLocalService
 * @generated
 */
@ProviderType
public class StNationalAssumptionsLocalServiceWrapper
	implements StNationalAssumptionsLocalService,
		ServiceWrapper<StNationalAssumptionsLocalService> {
	public StNationalAssumptionsLocalServiceWrapper(
		StNationalAssumptionsLocalService stNationalAssumptionsLocalService) {
		_stNationalAssumptionsLocalService = stNationalAssumptionsLocalService;
	}

	/**
	* Adds the st national assumptions to the database. Also notifies the appropriate model listeners.
	*
	* @param stNationalAssumptions the st national assumptions
	* @return the st national assumptions that was added
	*/
	@Override
	public com.stpl.app.model.StNationalAssumptions addStNationalAssumptions(
		com.stpl.app.model.StNationalAssumptions stNationalAssumptions) {
		return _stNationalAssumptionsLocalService.addStNationalAssumptions(stNationalAssumptions);
	}

	/**
	* Creates a new st national assumptions with the primary key. Does not add the st national assumptions to the database.
	*
	* @param stNationalAssumptionsPK the primary key for the new st national assumptions
	* @return the new st national assumptions
	*/
	@Override
	public com.stpl.app.model.StNationalAssumptions createStNationalAssumptions(
		com.stpl.app.service.persistence.StNationalAssumptionsPK stNationalAssumptionsPK) {
		return _stNationalAssumptionsLocalService.createStNationalAssumptions(stNationalAssumptionsPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stNationalAssumptionsLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the st national assumptions from the database. Also notifies the appropriate model listeners.
	*
	* @param stNationalAssumptions the st national assumptions
	* @return the st national assumptions that was removed
	*/
	@Override
	public com.stpl.app.model.StNationalAssumptions deleteStNationalAssumptions(
		com.stpl.app.model.StNationalAssumptions stNationalAssumptions) {
		return _stNationalAssumptionsLocalService.deleteStNationalAssumptions(stNationalAssumptions);
	}

	/**
	* Deletes the st national assumptions with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stNationalAssumptionsPK the primary key of the st national assumptions
	* @return the st national assumptions that was removed
	* @throws PortalException if a st national assumptions with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.StNationalAssumptions deleteStNationalAssumptions(
		com.stpl.app.service.persistence.StNationalAssumptionsPK stNationalAssumptionsPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stNationalAssumptionsLocalService.deleteStNationalAssumptions(stNationalAssumptionsPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _stNationalAssumptionsLocalService.dynamicQuery();
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
		return _stNationalAssumptionsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stNationalAssumptionsLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stNationalAssumptionsLocalService.dynamicQuery(dynamicQuery,
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
		return _stNationalAssumptionsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _stNationalAssumptionsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.StNationalAssumptions fetchStNationalAssumptions(
		com.stpl.app.service.persistence.StNationalAssumptionsPK stNationalAssumptionsPK) {
		return _stNationalAssumptionsLocalService.fetchStNationalAssumptions(stNationalAssumptionsPK);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _stNationalAssumptionsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stNationalAssumptionsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the st national assumptions with the primary key.
	*
	* @param stNationalAssumptionsPK the primary key of the st national assumptions
	* @return the st national assumptions
	* @throws PortalException if a st national assumptions with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.StNationalAssumptions getStNationalAssumptions(
		com.stpl.app.service.persistence.StNationalAssumptionsPK stNationalAssumptionsPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stNationalAssumptionsLocalService.getStNationalAssumptions(stNationalAssumptionsPK);
	}

	/**
	* Returns a range of all the st national assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st national assumptionses
	* @param end the upper bound of the range of st national assumptionses (not inclusive)
	* @return the range of st national assumptionses
	*/
	@Override
	public java.util.List<com.stpl.app.model.StNationalAssumptions> getStNationalAssumptionses(
		int start, int end) {
		return _stNationalAssumptionsLocalService.getStNationalAssumptionses(start,
			end);
	}

	/**
	* Returns the number of st national assumptionses.
	*
	* @return the number of st national assumptionses
	*/
	@Override
	public int getStNationalAssumptionsesCount() {
		return _stNationalAssumptionsLocalService.getStNationalAssumptionsesCount();
	}

	/**
	* Updates the st national assumptions in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param stNationalAssumptions the st national assumptions
	* @return the st national assumptions that was updated
	*/
	@Override
	public com.stpl.app.model.StNationalAssumptions updateStNationalAssumptions(
		com.stpl.app.model.StNationalAssumptions stNationalAssumptions) {
		return _stNationalAssumptionsLocalService.updateStNationalAssumptions(stNationalAssumptions);
	}

	@Override
	public StNationalAssumptionsLocalService getWrappedService() {
		return _stNationalAssumptionsLocalService;
	}

	@Override
	public void setWrappedService(
		StNationalAssumptionsLocalService stNationalAssumptionsLocalService) {
		_stNationalAssumptionsLocalService = stNationalAssumptionsLocalService;
	}

	private StNationalAssumptionsLocalService _stNationalAssumptionsLocalService;
}