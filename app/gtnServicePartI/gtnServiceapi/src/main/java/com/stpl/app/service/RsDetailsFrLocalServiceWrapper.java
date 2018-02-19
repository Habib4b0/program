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
 * Provides a wrapper for {@link RsDetailsFrLocalService}.
 *
 * @author
 * @see RsDetailsFrLocalService
 * @generated
 */
@ProviderType
public class RsDetailsFrLocalServiceWrapper implements RsDetailsFrLocalService,
	ServiceWrapper<RsDetailsFrLocalService> {
	public RsDetailsFrLocalServiceWrapper(
		RsDetailsFrLocalService rsDetailsFrLocalService) {
		_rsDetailsFrLocalService = rsDetailsFrLocalService;
	}

	/**
	* Adds the rs details fr to the database. Also notifies the appropriate model listeners.
	*
	* @param rsDetailsFr the rs details fr
	* @return the rs details fr that was added
	*/
	@Override
	public com.stpl.app.model.RsDetailsFr addRsDetailsFr(
		com.stpl.app.model.RsDetailsFr rsDetailsFr) {
		return _rsDetailsFrLocalService.addRsDetailsFr(rsDetailsFr);
	}

	/**
	* Creates a new rs details fr with the primary key. Does not add the rs details fr to the database.
	*
	* @param rsDetailsFrSid the primary key for the new rs details fr
	* @return the new rs details fr
	*/
	@Override
	public com.stpl.app.model.RsDetailsFr createRsDetailsFr(int rsDetailsFrSid) {
		return _rsDetailsFrLocalService.createRsDetailsFr(rsDetailsFrSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _rsDetailsFrLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the rs details fr with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param rsDetailsFrSid the primary key of the rs details fr
	* @return the rs details fr that was removed
	* @throws PortalException if a rs details fr with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.RsDetailsFr deleteRsDetailsFr(int rsDetailsFrSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _rsDetailsFrLocalService.deleteRsDetailsFr(rsDetailsFrSid);
	}

	/**
	* Deletes the rs details fr from the database. Also notifies the appropriate model listeners.
	*
	* @param rsDetailsFr the rs details fr
	* @return the rs details fr that was removed
	*/
	@Override
	public com.stpl.app.model.RsDetailsFr deleteRsDetailsFr(
		com.stpl.app.model.RsDetailsFr rsDetailsFr) {
		return _rsDetailsFrLocalService.deleteRsDetailsFr(rsDetailsFr);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _rsDetailsFrLocalService.dynamicQuery();
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
		return _rsDetailsFrLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _rsDetailsFrLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _rsDetailsFrLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _rsDetailsFrLocalService.dynamicQueryCount(dynamicQuery);
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
		return _rsDetailsFrLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.RsDetailsFr fetchRsDetailsFr(int rsDetailsFrSid) {
		return _rsDetailsFrLocalService.fetchRsDetailsFr(rsDetailsFrSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _rsDetailsFrLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _rsDetailsFrLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _rsDetailsFrLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _rsDetailsFrLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the rs details fr with the primary key.
	*
	* @param rsDetailsFrSid the primary key of the rs details fr
	* @return the rs details fr
	* @throws PortalException if a rs details fr with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.RsDetailsFr getRsDetailsFr(int rsDetailsFrSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _rsDetailsFrLocalService.getRsDetailsFr(rsDetailsFrSid);
	}

	/**
	* Returns a range of all the rs details frs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rs details frs
	* @param end the upper bound of the range of rs details frs (not inclusive)
	* @return the range of rs details frs
	*/
	@Override
	public java.util.List<com.stpl.app.model.RsDetailsFr> getRsDetailsFrs(
		int start, int end) {
		return _rsDetailsFrLocalService.getRsDetailsFrs(start, end);
	}

	/**
	* Returns the number of rs details frs.
	*
	* @return the number of rs details frs
	*/
	@Override
	public int getRsDetailsFrsCount() {
		return _rsDetailsFrLocalService.getRsDetailsFrsCount();
	}

	/**
	* Updates the rs details fr in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param rsDetailsFr the rs details fr
	* @return the rs details fr that was updated
	*/
	@Override
	public com.stpl.app.model.RsDetailsFr updateRsDetailsFr(
		com.stpl.app.model.RsDetailsFr rsDetailsFr) {
		return _rsDetailsFrLocalService.updateRsDetailsFr(rsDetailsFr);
	}

	@Override
	public RsDetailsFrLocalService getWrappedService() {
		return _rsDetailsFrLocalService;
	}

	@Override
	public void setWrappedService(
		RsDetailsFrLocalService rsDetailsFrLocalService) {
		_rsDetailsFrLocalService = rsDetailsFrLocalService;
	}

	private RsDetailsFrLocalService _rsDetailsFrLocalService;
}