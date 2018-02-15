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
 * Provides a wrapper for {@link HistCompanyGroupDetailsLocalService}.
 *
 * @author
 * @see HistCompanyGroupDetailsLocalService
 * @generated
 */
@ProviderType
public class HistCompanyGroupDetailsLocalServiceWrapper
	implements HistCompanyGroupDetailsLocalService,
		ServiceWrapper<HistCompanyGroupDetailsLocalService> {
	public HistCompanyGroupDetailsLocalServiceWrapper(
		HistCompanyGroupDetailsLocalService histCompanyGroupDetailsLocalService) {
		_histCompanyGroupDetailsLocalService = histCompanyGroupDetailsLocalService;
	}

	/**
	* Adds the hist company group details to the database. Also notifies the appropriate model listeners.
	*
	* @param histCompanyGroupDetails the hist company group details
	* @return the hist company group details that was added
	*/
	@Override
	public com.stpl.app.model.HistCompanyGroupDetails addHistCompanyGroupDetails(
		com.stpl.app.model.HistCompanyGroupDetails histCompanyGroupDetails) {
		return _histCompanyGroupDetailsLocalService.addHistCompanyGroupDetails(histCompanyGroupDetails);
	}

	/**
	* Creates a new hist company group details with the primary key. Does not add the hist company group details to the database.
	*
	* @param histCompanyGroupDetailsPK the primary key for the new hist company group details
	* @return the new hist company group details
	*/
	@Override
	public com.stpl.app.model.HistCompanyGroupDetails createHistCompanyGroupDetails(
		com.stpl.app.service.persistence.HistCompanyGroupDetailsPK histCompanyGroupDetailsPK) {
		return _histCompanyGroupDetailsLocalService.createHistCompanyGroupDetails(histCompanyGroupDetailsPK);
	}

	/**
	* Deletes the hist company group details from the database. Also notifies the appropriate model listeners.
	*
	* @param histCompanyGroupDetails the hist company group details
	* @return the hist company group details that was removed
	*/
	@Override
	public com.stpl.app.model.HistCompanyGroupDetails deleteHistCompanyGroupDetails(
		com.stpl.app.model.HistCompanyGroupDetails histCompanyGroupDetails) {
		return _histCompanyGroupDetailsLocalService.deleteHistCompanyGroupDetails(histCompanyGroupDetails);
	}

	/**
	* Deletes the hist company group details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param histCompanyGroupDetailsPK the primary key of the hist company group details
	* @return the hist company group details that was removed
	* @throws PortalException if a hist company group details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.HistCompanyGroupDetails deleteHistCompanyGroupDetails(
		com.stpl.app.service.persistence.HistCompanyGroupDetailsPK histCompanyGroupDetailsPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _histCompanyGroupDetailsLocalService.deleteHistCompanyGroupDetails(histCompanyGroupDetailsPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _histCompanyGroupDetailsLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _histCompanyGroupDetailsLocalService.dynamicQuery();
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
		return _histCompanyGroupDetailsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistCompanyGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _histCompanyGroupDetailsLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistCompanyGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _histCompanyGroupDetailsLocalService.dynamicQuery(dynamicQuery,
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
		return _histCompanyGroupDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _histCompanyGroupDetailsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.HistCompanyGroupDetails fetchHistCompanyGroupDetails(
		com.stpl.app.service.persistence.HistCompanyGroupDetailsPK histCompanyGroupDetailsPK) {
		return _histCompanyGroupDetailsLocalService.fetchHistCompanyGroupDetails(histCompanyGroupDetailsPK);
	}

	/**
	* Returns the hist company group details with the primary key.
	*
	* @param histCompanyGroupDetailsPK the primary key of the hist company group details
	* @return the hist company group details
	* @throws PortalException if a hist company group details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.HistCompanyGroupDetails getHistCompanyGroupDetails(
		com.stpl.app.service.persistence.HistCompanyGroupDetailsPK histCompanyGroupDetailsPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _histCompanyGroupDetailsLocalService.getHistCompanyGroupDetails(histCompanyGroupDetailsPK);
	}

	/**
	* Returns a range of all the hist company group detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistCompanyGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist company group detailses
	* @param end the upper bound of the range of hist company group detailses (not inclusive)
	* @return the range of hist company group detailses
	*/
	@Override
	public java.util.List<com.stpl.app.model.HistCompanyGroupDetails> getHistCompanyGroupDetailses(
		int start, int end) {
		return _histCompanyGroupDetailsLocalService.getHistCompanyGroupDetailses(start,
			end);
	}

	/**
	* Returns the number of hist company group detailses.
	*
	* @return the number of hist company group detailses
	*/
	@Override
	public int getHistCompanyGroupDetailsesCount() {
		return _histCompanyGroupDetailsLocalService.getHistCompanyGroupDetailsesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _histCompanyGroupDetailsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _histCompanyGroupDetailsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the hist company group details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param histCompanyGroupDetails the hist company group details
	* @return the hist company group details that was updated
	*/
	@Override
	public com.stpl.app.model.HistCompanyGroupDetails updateHistCompanyGroupDetails(
		com.stpl.app.model.HistCompanyGroupDetails histCompanyGroupDetails) {
		return _histCompanyGroupDetailsLocalService.updateHistCompanyGroupDetails(histCompanyGroupDetails);
	}

	@Override
	public HistCompanyGroupDetailsLocalService getWrappedService() {
		return _histCompanyGroupDetailsLocalService;
	}

	@Override
	public void setWrappedService(
		HistCompanyGroupDetailsLocalService histCompanyGroupDetailsLocalService) {
		_histCompanyGroupDetailsLocalService = histCompanyGroupDetailsLocalService;
	}

	private HistCompanyGroupDetailsLocalService _histCompanyGroupDetailsLocalService;
}