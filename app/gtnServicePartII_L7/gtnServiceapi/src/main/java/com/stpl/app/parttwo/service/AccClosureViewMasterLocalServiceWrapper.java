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
 * Provides a wrapper for {@link AccClosureViewMasterLocalService}.
 *
 * @author
 * @see AccClosureViewMasterLocalService
 * @generated
 */
@ProviderType
public class AccClosureViewMasterLocalServiceWrapper
	implements AccClosureViewMasterLocalService,
		ServiceWrapper<AccClosureViewMasterLocalService> {
	public AccClosureViewMasterLocalServiceWrapper(
		AccClosureViewMasterLocalService accClosureViewMasterLocalService) {
		_accClosureViewMasterLocalService = accClosureViewMasterLocalService;
	}

	/**
	* Adds the acc closure view master to the database. Also notifies the appropriate model listeners.
	*
	* @param accClosureViewMaster the acc closure view master
	* @return the acc closure view master that was added
	*/
	@Override
	public com.stpl.app.parttwo.model.AccClosureViewMaster addAccClosureViewMaster(
		com.stpl.app.parttwo.model.AccClosureViewMaster accClosureViewMaster) {
		return _accClosureViewMasterLocalService.addAccClosureViewMaster(accClosureViewMaster);
	}

	/**
	* Creates a new acc closure view master with the primary key. Does not add the acc closure view master to the database.
	*
	* @param accClosureViewMasterSid the primary key for the new acc closure view master
	* @return the new acc closure view master
	*/
	@Override
	public com.stpl.app.parttwo.model.AccClosureViewMaster createAccClosureViewMaster(
		int accClosureViewMasterSid) {
		return _accClosureViewMasterLocalService.createAccClosureViewMaster(accClosureViewMasterSid);
	}

	/**
	* Deletes the acc closure view master from the database. Also notifies the appropriate model listeners.
	*
	* @param accClosureViewMaster the acc closure view master
	* @return the acc closure view master that was removed
	*/
	@Override
	public com.stpl.app.parttwo.model.AccClosureViewMaster deleteAccClosureViewMaster(
		com.stpl.app.parttwo.model.AccClosureViewMaster accClosureViewMaster) {
		return _accClosureViewMasterLocalService.deleteAccClosureViewMaster(accClosureViewMaster);
	}

	/**
	* Deletes the acc closure view master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accClosureViewMasterSid the primary key of the acc closure view master
	* @return the acc closure view master that was removed
	* @throws PortalException if a acc closure view master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.AccClosureViewMaster deleteAccClosureViewMaster(
		int accClosureViewMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accClosureViewMasterLocalService.deleteAccClosureViewMaster(accClosureViewMasterSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accClosureViewMasterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _accClosureViewMasterLocalService.dynamicQuery();
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
		return _accClosureViewMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AccClosureViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _accClosureViewMasterLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AccClosureViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _accClosureViewMasterLocalService.dynamicQuery(dynamicQuery,
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
		return _accClosureViewMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _accClosureViewMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.parttwo.model.AccClosureViewMaster fetchAccClosureViewMaster(
		int accClosureViewMasterSid) {
		return _accClosureViewMasterLocalService.fetchAccClosureViewMaster(accClosureViewMasterSid);
	}

	/**
	* Returns the acc closure view master with the primary key.
	*
	* @param accClosureViewMasterSid the primary key of the acc closure view master
	* @return the acc closure view master
	* @throws PortalException if a acc closure view master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.AccClosureViewMaster getAccClosureViewMaster(
		int accClosureViewMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accClosureViewMasterLocalService.getAccClosureViewMaster(accClosureViewMasterSid);
	}

	/**
	* Returns a range of all the acc closure view masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AccClosureViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of acc closure view masters
	* @param end the upper bound of the range of acc closure view masters (not inclusive)
	* @return the range of acc closure view masters
	*/
	@Override
	public java.util.List<com.stpl.app.parttwo.model.AccClosureViewMaster> getAccClosureViewMasters(
		int start, int end) {
		return _accClosureViewMasterLocalService.getAccClosureViewMasters(start,
			end);
	}

	/**
	* Returns the number of acc closure view masters.
	*
	* @return the number of acc closure view masters
	*/
	@Override
	public int getAccClosureViewMastersCount() {
		return _accClosureViewMasterLocalService.getAccClosureViewMastersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _accClosureViewMasterLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _accClosureViewMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _accClosureViewMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accClosureViewMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the acc closure view master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accClosureViewMaster the acc closure view master
	* @return the acc closure view master that was updated
	*/
	@Override
	public com.stpl.app.parttwo.model.AccClosureViewMaster updateAccClosureViewMaster(
		com.stpl.app.parttwo.model.AccClosureViewMaster accClosureViewMaster) {
		return _accClosureViewMasterLocalService.updateAccClosureViewMaster(accClosureViewMaster);
	}

	@Override
	public AccClosureViewMasterLocalService getWrappedService() {
		return _accClosureViewMasterLocalService;
	}

	@Override
	public void setWrappedService(
		AccClosureViewMasterLocalService accClosureViewMasterLocalService) {
		_accClosureViewMasterLocalService = accClosureViewMasterLocalService;
	}

	private AccClosureViewMasterLocalService _accClosureViewMasterLocalService;
}