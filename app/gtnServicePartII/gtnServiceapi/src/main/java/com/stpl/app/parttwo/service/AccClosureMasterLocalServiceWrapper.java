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
 * Provides a wrapper for {@link AccClosureMasterLocalService}.
 *
 * @author
 * @see AccClosureMasterLocalService
 * @generated
 */
@ProviderType
public class AccClosureMasterLocalServiceWrapper
	implements AccClosureMasterLocalService,
		ServiceWrapper<AccClosureMasterLocalService> {
	public AccClosureMasterLocalServiceWrapper(
		AccClosureMasterLocalService accClosureMasterLocalService) {
		_accClosureMasterLocalService = accClosureMasterLocalService;
	}

	/**
	* Adds the acc closure master to the database. Also notifies the appropriate model listeners.
	*
	* @param accClosureMaster the acc closure master
	* @return the acc closure master that was added
	*/
	@Override
	public com.stpl.app.parttwo.model.AccClosureMaster addAccClosureMaster(
		com.stpl.app.parttwo.model.AccClosureMaster accClosureMaster) {
		return _accClosureMasterLocalService.addAccClosureMaster(accClosureMaster);
	}

	/**
	* Creates a new acc closure master with the primary key. Does not add the acc closure master to the database.
	*
	* @param accClosureMasterSid the primary key for the new acc closure master
	* @return the new acc closure master
	*/
	@Override
	public com.stpl.app.parttwo.model.AccClosureMaster createAccClosureMaster(
		int accClosureMasterSid) {
		return _accClosureMasterLocalService.createAccClosureMaster(accClosureMasterSid);
	}

	/**
	* Deletes the acc closure master from the database. Also notifies the appropriate model listeners.
	*
	* @param accClosureMaster the acc closure master
	* @return the acc closure master that was removed
	*/
	@Override
	public com.stpl.app.parttwo.model.AccClosureMaster deleteAccClosureMaster(
		com.stpl.app.parttwo.model.AccClosureMaster accClosureMaster) {
		return _accClosureMasterLocalService.deleteAccClosureMaster(accClosureMaster);
	}

	/**
	* Deletes the acc closure master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accClosureMasterSid the primary key of the acc closure master
	* @return the acc closure master that was removed
	* @throws PortalException if a acc closure master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.AccClosureMaster deleteAccClosureMaster(
		int accClosureMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accClosureMasterLocalService.deleteAccClosureMaster(accClosureMasterSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accClosureMasterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _accClosureMasterLocalService.dynamicQuery();
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
		return _accClosureMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AccClosureMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _accClosureMasterLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AccClosureMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _accClosureMasterLocalService.dynamicQuery(dynamicQuery, start,
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
		return _accClosureMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _accClosureMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.parttwo.model.AccClosureMaster fetchAccClosureMaster(
		int accClosureMasterSid) {
		return _accClosureMasterLocalService.fetchAccClosureMaster(accClosureMasterSid);
	}

	/**
	* Returns the acc closure master with the primary key.
	*
	* @param accClosureMasterSid the primary key of the acc closure master
	* @return the acc closure master
	* @throws PortalException if a acc closure master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.AccClosureMaster getAccClosureMaster(
		int accClosureMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accClosureMasterLocalService.getAccClosureMaster(accClosureMasterSid);
	}

	/**
	* Returns a range of all the acc closure masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AccClosureMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of acc closure masters
	* @param end the upper bound of the range of acc closure masters (not inclusive)
	* @return the range of acc closure masters
	*/
	@Override
	public java.util.List<com.stpl.app.parttwo.model.AccClosureMaster> getAccClosureMasters(
		int start, int end) {
		return _accClosureMasterLocalService.getAccClosureMasters(start, end);
	}

	/**
	* Returns the number of acc closure masters.
	*
	* @return the number of acc closure masters
	*/
	@Override
	public int getAccClosureMastersCount() {
		return _accClosureMasterLocalService.getAccClosureMastersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _accClosureMasterLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _accClosureMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _accClosureMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accClosureMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the acc closure master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accClosureMaster the acc closure master
	* @return the acc closure master that was updated
	*/
	@Override
	public com.stpl.app.parttwo.model.AccClosureMaster updateAccClosureMaster(
		com.stpl.app.parttwo.model.AccClosureMaster accClosureMaster) {
		return _accClosureMasterLocalService.updateAccClosureMaster(accClosureMaster);
	}

	@Override
	public AccClosureMasterLocalService getWrappedService() {
		return _accClosureMasterLocalService;
	}

	@Override
	public void setWrappedService(
		AccClosureMasterLocalService accClosureMasterLocalService) {
		_accClosureMasterLocalService = accClosureMasterLocalService;
	}

	private AccClosureMasterLocalService _accClosureMasterLocalService;
}