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
 * Provides a wrapper for {@link StChAssumptionsLocalService}.
 *
 * @author
 * @see StChAssumptionsLocalService
 * @generated
 */
@ProviderType
public class StChAssumptionsLocalServiceWrapper
	implements StChAssumptionsLocalService,
		ServiceWrapper<StChAssumptionsLocalService> {
	public StChAssumptionsLocalServiceWrapper(
		StChAssumptionsLocalService stChAssumptionsLocalService) {
		_stChAssumptionsLocalService = stChAssumptionsLocalService;
	}

	/**
	* Adds the st ch assumptions to the database. Also notifies the appropriate model listeners.
	*
	* @param stChAssumptions the st ch assumptions
	* @return the st ch assumptions that was added
	*/
	@Override
	public com.stpl.app.model.StChAssumptions addStChAssumptions(
		com.stpl.app.model.StChAssumptions stChAssumptions) {
		return _stChAssumptionsLocalService.addStChAssumptions(stChAssumptions);
	}

	/**
	* Creates a new st ch assumptions with the primary key. Does not add the st ch assumptions to the database.
	*
	* @param stChAssumptionsPK the primary key for the new st ch assumptions
	* @return the new st ch assumptions
	*/
	@Override
	public com.stpl.app.model.StChAssumptions createStChAssumptions(
		com.stpl.app.service.persistence.StChAssumptionsPK stChAssumptionsPK) {
		return _stChAssumptionsLocalService.createStChAssumptions(stChAssumptionsPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stChAssumptionsLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the st ch assumptions from the database. Also notifies the appropriate model listeners.
	*
	* @param stChAssumptions the st ch assumptions
	* @return the st ch assumptions that was removed
	*/
	@Override
	public com.stpl.app.model.StChAssumptions deleteStChAssumptions(
		com.stpl.app.model.StChAssumptions stChAssumptions) {
		return _stChAssumptionsLocalService.deleteStChAssumptions(stChAssumptions);
	}

	/**
	* Deletes the st ch assumptions with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stChAssumptionsPK the primary key of the st ch assumptions
	* @return the st ch assumptions that was removed
	* @throws PortalException if a st ch assumptions with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.StChAssumptions deleteStChAssumptions(
		com.stpl.app.service.persistence.StChAssumptionsPK stChAssumptionsPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stChAssumptionsLocalService.deleteStChAssumptions(stChAssumptionsPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _stChAssumptionsLocalService.dynamicQuery();
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
		return _stChAssumptionsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stChAssumptionsLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stChAssumptionsLocalService.dynamicQuery(dynamicQuery, start,
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
		return _stChAssumptionsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _stChAssumptionsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.StChAssumptions fetchStChAssumptions(
		com.stpl.app.service.persistence.StChAssumptionsPK stChAssumptionsPK) {
		return _stChAssumptionsLocalService.fetchStChAssumptions(stChAssumptionsPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _stChAssumptionsLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _stChAssumptionsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _stChAssumptionsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stChAssumptionsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the st ch assumptions with the primary key.
	*
	* @param stChAssumptionsPK the primary key of the st ch assumptions
	* @return the st ch assumptions
	* @throws PortalException if a st ch assumptions with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.StChAssumptions getStChAssumptions(
		com.stpl.app.service.persistence.StChAssumptionsPK stChAssumptionsPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stChAssumptionsLocalService.getStChAssumptions(stChAssumptionsPK);
	}

	/**
	* Returns a range of all the st ch assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st ch assumptionses
	* @param end the upper bound of the range of st ch assumptionses (not inclusive)
	* @return the range of st ch assumptionses
	*/
	@Override
	public java.util.List<com.stpl.app.model.StChAssumptions> getStChAssumptionses(
		int start, int end) {
		return _stChAssumptionsLocalService.getStChAssumptionses(start, end);
	}

	/**
	* Returns the number of st ch assumptionses.
	*
	* @return the number of st ch assumptionses
	*/
	@Override
	public int getStChAssumptionsesCount() {
		return _stChAssumptionsLocalService.getStChAssumptionsesCount();
	}

	/**
	* Updates the st ch assumptions in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param stChAssumptions the st ch assumptions
	* @return the st ch assumptions that was updated
	*/
	@Override
	public com.stpl.app.model.StChAssumptions updateStChAssumptions(
		com.stpl.app.model.StChAssumptions stChAssumptions) {
		return _stChAssumptionsLocalService.updateStChAssumptions(stChAssumptions);
	}

	@Override
	public StChAssumptionsLocalService getWrappedService() {
		return _stChAssumptionsLocalService;
	}

	@Override
	public void setWrappedService(
		StChAssumptionsLocalService stChAssumptionsLocalService) {
		_stChAssumptionsLocalService = stChAssumptionsLocalService;
	}

	private StChAssumptionsLocalService _stChAssumptionsLocalService;
}