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
 * Provides a wrapper for {@link GlBalanceMasterLocalService}.
 *
 * @author
 * @see GlBalanceMasterLocalService
 * @generated
 */
@ProviderType
public class GlBalanceMasterLocalServiceWrapper
	implements GlBalanceMasterLocalService,
		ServiceWrapper<GlBalanceMasterLocalService> {
	public GlBalanceMasterLocalServiceWrapper(
		GlBalanceMasterLocalService glBalanceMasterLocalService) {
		_glBalanceMasterLocalService = glBalanceMasterLocalService;
	}

	/**
	* Adds the gl balance master to the database. Also notifies the appropriate model listeners.
	*
	* @param glBalanceMaster the gl balance master
	* @return the gl balance master that was added
	*/
	@Override
	public com.stpl.app.model.GlBalanceMaster addGlBalanceMaster(
		com.stpl.app.model.GlBalanceMaster glBalanceMaster) {
		return _glBalanceMasterLocalService.addGlBalanceMaster(glBalanceMaster);
	}

	/**
	* Creates a new gl balance master with the primary key. Does not add the gl balance master to the database.
	*
	* @param glBalanceMasterSid the primary key for the new gl balance master
	* @return the new gl balance master
	*/
	@Override
	public com.stpl.app.model.GlBalanceMaster createGlBalanceMaster(
		int glBalanceMasterSid) {
		return _glBalanceMasterLocalService.createGlBalanceMaster(glBalanceMasterSid);
	}

	/**
	* Deletes the gl balance master from the database. Also notifies the appropriate model listeners.
	*
	* @param glBalanceMaster the gl balance master
	* @return the gl balance master that was removed
	*/
	@Override
	public com.stpl.app.model.GlBalanceMaster deleteGlBalanceMaster(
		com.stpl.app.model.GlBalanceMaster glBalanceMaster) {
		return _glBalanceMasterLocalService.deleteGlBalanceMaster(glBalanceMaster);
	}

	/**
	* Deletes the gl balance master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param glBalanceMasterSid the primary key of the gl balance master
	* @return the gl balance master that was removed
	* @throws PortalException if a gl balance master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.GlBalanceMaster deleteGlBalanceMaster(
		int glBalanceMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _glBalanceMasterLocalService.deleteGlBalanceMaster(glBalanceMasterSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _glBalanceMasterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _glBalanceMasterLocalService.dynamicQuery();
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
		return _glBalanceMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _glBalanceMasterLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _glBalanceMasterLocalService.dynamicQuery(dynamicQuery, start,
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
		return _glBalanceMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _glBalanceMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.GlBalanceMaster fetchGlBalanceMaster(
		int glBalanceMasterSid) {
		return _glBalanceMasterLocalService.fetchGlBalanceMaster(glBalanceMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _glBalanceMasterLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the gl balance master with the primary key.
	*
	* @param glBalanceMasterSid the primary key of the gl balance master
	* @return the gl balance master
	* @throws PortalException if a gl balance master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.GlBalanceMaster getGlBalanceMaster(
		int glBalanceMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _glBalanceMasterLocalService.getGlBalanceMaster(glBalanceMasterSid);
	}

	/**
	* Returns a range of all the gl balance masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @return the range of gl balance masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.GlBalanceMaster> getGlBalanceMasters(
		int start, int end) {
		return _glBalanceMasterLocalService.getGlBalanceMasters(start, end);
	}

	/**
	* Returns the number of gl balance masters.
	*
	* @return the number of gl balance masters
	*/
	@Override
	public int getGlBalanceMastersCount() {
		return _glBalanceMasterLocalService.getGlBalanceMastersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _glBalanceMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _glBalanceMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _glBalanceMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the gl balance master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param glBalanceMaster the gl balance master
	* @return the gl balance master that was updated
	*/
	@Override
	public com.stpl.app.model.GlBalanceMaster updateGlBalanceMaster(
		com.stpl.app.model.GlBalanceMaster glBalanceMaster) {
		return _glBalanceMasterLocalService.updateGlBalanceMaster(glBalanceMaster);
	}

	@Override
	public GlBalanceMasterLocalService getWrappedService() {
		return _glBalanceMasterLocalService;
	}

	@Override
	public void setWrappedService(
		GlBalanceMasterLocalService glBalanceMasterLocalService) {
		_glBalanceMasterLocalService = glBalanceMasterLocalService;
	}

	private GlBalanceMasterLocalService _glBalanceMasterLocalService;
}