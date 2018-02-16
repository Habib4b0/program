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
 * Provides a wrapper for {@link NationalAssumptionsLocalService}.
 *
 * @author
 * @see NationalAssumptionsLocalService
 * @generated
 */
@ProviderType
public class NationalAssumptionsLocalServiceWrapper
	implements NationalAssumptionsLocalService,
		ServiceWrapper<NationalAssumptionsLocalService> {
	public NationalAssumptionsLocalServiceWrapper(
		NationalAssumptionsLocalService nationalAssumptionsLocalService) {
		_nationalAssumptionsLocalService = nationalAssumptionsLocalService;
	}

	/**
	* Adds the national assumptions to the database. Also notifies the appropriate model listeners.
	*
	* @param nationalAssumptions the national assumptions
	* @return the national assumptions that was added
	*/
	@Override
	public com.stpl.app.model.NationalAssumptions addNationalAssumptions(
		com.stpl.app.model.NationalAssumptions nationalAssumptions) {
		return _nationalAssumptionsLocalService.addNationalAssumptions(nationalAssumptions);
	}

	/**
	* Creates a new national assumptions with the primary key. Does not add the national assumptions to the database.
	*
	* @param nationalAssumptionsPK the primary key for the new national assumptions
	* @return the new national assumptions
	*/
	@Override
	public com.stpl.app.model.NationalAssumptions createNationalAssumptions(
		com.stpl.app.service.persistence.NationalAssumptionsPK nationalAssumptionsPK) {
		return _nationalAssumptionsLocalService.createNationalAssumptions(nationalAssumptionsPK);
	}

	/**
	* Deletes the national assumptions from the database. Also notifies the appropriate model listeners.
	*
	* @param nationalAssumptions the national assumptions
	* @return the national assumptions that was removed
	*/
	@Override
	public com.stpl.app.model.NationalAssumptions deleteNationalAssumptions(
		com.stpl.app.model.NationalAssumptions nationalAssumptions) {
		return _nationalAssumptionsLocalService.deleteNationalAssumptions(nationalAssumptions);
	}

	/**
	* Deletes the national assumptions with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param nationalAssumptionsPK the primary key of the national assumptions
	* @return the national assumptions that was removed
	* @throws PortalException if a national assumptions with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.NationalAssumptions deleteNationalAssumptions(
		com.stpl.app.service.persistence.NationalAssumptionsPK nationalAssumptionsPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _nationalAssumptionsLocalService.deleteNationalAssumptions(nationalAssumptionsPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _nationalAssumptionsLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _nationalAssumptionsLocalService.dynamicQuery();
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
		return _nationalAssumptionsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _nationalAssumptionsLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _nationalAssumptionsLocalService.dynamicQuery(dynamicQuery,
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
		return _nationalAssumptionsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _nationalAssumptionsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.NationalAssumptions fetchNationalAssumptions(
		com.stpl.app.service.persistence.NationalAssumptionsPK nationalAssumptionsPK) {
		return _nationalAssumptionsLocalService.fetchNationalAssumptions(nationalAssumptionsPK);
	}

	/**
	* Returns the national assumptions with the primary key.
	*
	* @param nationalAssumptionsPK the primary key of the national assumptions
	* @return the national assumptions
	* @throws PortalException if a national assumptions with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.NationalAssumptions getNationalAssumptions(
		com.stpl.app.service.persistence.NationalAssumptionsPK nationalAssumptionsPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _nationalAssumptionsLocalService.getNationalAssumptions(nationalAssumptionsPK);
	}

	/**
	* Returns a range of all the national assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of national assumptionses
	* @param end the upper bound of the range of national assumptionses (not inclusive)
	* @return the range of national assumptionses
	*/
	@Override
	public java.util.List<com.stpl.app.model.NationalAssumptions> getNationalAssumptionses(
		int start, int end) {
		return _nationalAssumptionsLocalService.getNationalAssumptionses(start,
			end);
	}

	/**
	* Returns the number of national assumptionses.
	*
	* @return the number of national assumptionses
	*/
	@Override
	public int getNationalAssumptionsesCount() {
		return _nationalAssumptionsLocalService.getNationalAssumptionsesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _nationalAssumptionsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _nationalAssumptionsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the national assumptions in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param nationalAssumptions the national assumptions
	* @return the national assumptions that was updated
	*/
	@Override
	public com.stpl.app.model.NationalAssumptions updateNationalAssumptions(
		com.stpl.app.model.NationalAssumptions nationalAssumptions) {
		return _nationalAssumptionsLocalService.updateNationalAssumptions(nationalAssumptions);
	}

	@Override
	public NationalAssumptionsLocalService getWrappedService() {
		return _nationalAssumptionsLocalService;
	}

	@Override
	public void setWrappedService(
		NationalAssumptionsLocalService nationalAssumptionsLocalService) {
		_nationalAssumptionsLocalService = nationalAssumptionsLocalService;
	}

	private NationalAssumptionsLocalService _nationalAssumptionsLocalService;
}