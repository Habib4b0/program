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
 * Provides a wrapper for {@link ChSalesProjectionMasterLocalService}.
 *
 * @author
 * @see ChSalesProjectionMasterLocalService
 * @generated
 */
@ProviderType
public class ChSalesProjectionMasterLocalServiceWrapper
	implements ChSalesProjectionMasterLocalService,
		ServiceWrapper<ChSalesProjectionMasterLocalService> {
	public ChSalesProjectionMasterLocalServiceWrapper(
		ChSalesProjectionMasterLocalService chSalesProjectionMasterLocalService) {
		_chSalesProjectionMasterLocalService = chSalesProjectionMasterLocalService;
	}

	/**
	* Adds the ch sales projection master to the database. Also notifies the appropriate model listeners.
	*
	* @param chSalesProjectionMaster the ch sales projection master
	* @return the ch sales projection master that was added
	*/
	@Override
	public com.stpl.app.model.ChSalesProjectionMaster addChSalesProjectionMaster(
		com.stpl.app.model.ChSalesProjectionMaster chSalesProjectionMaster) {
		return _chSalesProjectionMasterLocalService.addChSalesProjectionMaster(chSalesProjectionMaster);
	}

	/**
	* Creates a new ch sales projection master with the primary key. Does not add the ch sales projection master to the database.
	*
	* @param projectionDetailsSid the primary key for the new ch sales projection master
	* @return the new ch sales projection master
	*/
	@Override
	public com.stpl.app.model.ChSalesProjectionMaster createChSalesProjectionMaster(
		int projectionDetailsSid) {
		return _chSalesProjectionMasterLocalService.createChSalesProjectionMaster(projectionDetailsSid);
	}

	/**
	* Deletes the ch sales projection master from the database. Also notifies the appropriate model listeners.
	*
	* @param chSalesProjectionMaster the ch sales projection master
	* @return the ch sales projection master that was removed
	*/
	@Override
	public com.stpl.app.model.ChSalesProjectionMaster deleteChSalesProjectionMaster(
		com.stpl.app.model.ChSalesProjectionMaster chSalesProjectionMaster) {
		return _chSalesProjectionMasterLocalService.deleteChSalesProjectionMaster(chSalesProjectionMaster);
	}

	/**
	* Deletes the ch sales projection master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectionDetailsSid the primary key of the ch sales projection master
	* @return the ch sales projection master that was removed
	* @throws PortalException if a ch sales projection master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ChSalesProjectionMaster deleteChSalesProjectionMaster(
		int projectionDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _chSalesProjectionMasterLocalService.deleteChSalesProjectionMaster(projectionDetailsSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _chSalesProjectionMasterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _chSalesProjectionMasterLocalService.dynamicQuery();
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
		return _chSalesProjectionMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _chSalesProjectionMasterLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _chSalesProjectionMasterLocalService.dynamicQuery(dynamicQuery,
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
		return _chSalesProjectionMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _chSalesProjectionMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.ChSalesProjectionMaster fetchChSalesProjectionMaster(
		int projectionDetailsSid) {
		return _chSalesProjectionMasterLocalService.fetchChSalesProjectionMaster(projectionDetailsSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _chSalesProjectionMasterLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the ch sales projection master with the primary key.
	*
	* @param projectionDetailsSid the primary key of the ch sales projection master
	* @return the ch sales projection master
	* @throws PortalException if a ch sales projection master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ChSalesProjectionMaster getChSalesProjectionMaster(
		int projectionDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _chSalesProjectionMasterLocalService.getChSalesProjectionMaster(projectionDetailsSid);
	}

	/**
	* Returns a range of all the ch sales projection masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch sales projection masters
	* @param end the upper bound of the range of ch sales projection masters (not inclusive)
	* @return the range of ch sales projection masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.ChSalesProjectionMaster> getChSalesProjectionMasters(
		int start, int end) {
		return _chSalesProjectionMasterLocalService.getChSalesProjectionMasters(start,
			end);
	}

	/**
	* Returns the number of ch sales projection masters.
	*
	* @return the number of ch sales projection masters
	*/
	@Override
	public int getChSalesProjectionMastersCount() {
		return _chSalesProjectionMasterLocalService.getChSalesProjectionMastersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _chSalesProjectionMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _chSalesProjectionMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _chSalesProjectionMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the ch sales projection master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param chSalesProjectionMaster the ch sales projection master
	* @return the ch sales projection master that was updated
	*/
	@Override
	public com.stpl.app.model.ChSalesProjectionMaster updateChSalesProjectionMaster(
		com.stpl.app.model.ChSalesProjectionMaster chSalesProjectionMaster) {
		return _chSalesProjectionMasterLocalService.updateChSalesProjectionMaster(chSalesProjectionMaster);
	}

	@Override
	public ChSalesProjectionMasterLocalService getWrappedService() {
		return _chSalesProjectionMasterLocalService;
	}

	@Override
	public void setWrappedService(
		ChSalesProjectionMasterLocalService chSalesProjectionMasterLocalService) {
		_chSalesProjectionMasterLocalService = chSalesProjectionMasterLocalService;
	}

	private ChSalesProjectionMasterLocalService _chSalesProjectionMasterLocalService;
}