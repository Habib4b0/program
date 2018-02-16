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
 * Provides a wrapper for {@link StChDiscountProjMasterLocalService}.
 *
 * @author
 * @see StChDiscountProjMasterLocalService
 * @generated
 */
@ProviderType
public class StChDiscountProjMasterLocalServiceWrapper
	implements StChDiscountProjMasterLocalService,
		ServiceWrapper<StChDiscountProjMasterLocalService> {
	public StChDiscountProjMasterLocalServiceWrapper(
		StChDiscountProjMasterLocalService stChDiscountProjMasterLocalService) {
		_stChDiscountProjMasterLocalService = stChDiscountProjMasterLocalService;
	}

	/**
	* Adds the st ch discount proj master to the database. Also notifies the appropriate model listeners.
	*
	* @param stChDiscountProjMaster the st ch discount proj master
	* @return the st ch discount proj master that was added
	*/
	@Override
	public com.stpl.app.model.StChDiscountProjMaster addStChDiscountProjMaster(
		com.stpl.app.model.StChDiscountProjMaster stChDiscountProjMaster) {
		return _stChDiscountProjMasterLocalService.addStChDiscountProjMaster(stChDiscountProjMaster);
	}

	/**
	* Creates a new st ch discount proj master with the primary key. Does not add the st ch discount proj master to the database.
	*
	* @param stChDiscountProjMasterPK the primary key for the new st ch discount proj master
	* @return the new st ch discount proj master
	*/
	@Override
	public com.stpl.app.model.StChDiscountProjMaster createStChDiscountProjMaster(
		com.stpl.app.service.persistence.StChDiscountProjMasterPK stChDiscountProjMasterPK) {
		return _stChDiscountProjMasterLocalService.createStChDiscountProjMaster(stChDiscountProjMasterPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stChDiscountProjMasterLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the st ch discount proj master from the database. Also notifies the appropriate model listeners.
	*
	* @param stChDiscountProjMaster the st ch discount proj master
	* @return the st ch discount proj master that was removed
	*/
	@Override
	public com.stpl.app.model.StChDiscountProjMaster deleteStChDiscountProjMaster(
		com.stpl.app.model.StChDiscountProjMaster stChDiscountProjMaster) {
		return _stChDiscountProjMasterLocalService.deleteStChDiscountProjMaster(stChDiscountProjMaster);
	}

	/**
	* Deletes the st ch discount proj master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stChDiscountProjMasterPK the primary key of the st ch discount proj master
	* @return the st ch discount proj master that was removed
	* @throws PortalException if a st ch discount proj master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.StChDiscountProjMaster deleteStChDiscountProjMaster(
		com.stpl.app.service.persistence.StChDiscountProjMasterPK stChDiscountProjMasterPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stChDiscountProjMasterLocalService.deleteStChDiscountProjMaster(stChDiscountProjMasterPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _stChDiscountProjMasterLocalService.dynamicQuery();
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
		return _stChDiscountProjMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stChDiscountProjMasterLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stChDiscountProjMasterLocalService.dynamicQuery(dynamicQuery,
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
		return _stChDiscountProjMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _stChDiscountProjMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.StChDiscountProjMaster fetchStChDiscountProjMaster(
		com.stpl.app.service.persistence.StChDiscountProjMasterPK stChDiscountProjMasterPK) {
		return _stChDiscountProjMasterLocalService.fetchStChDiscountProjMaster(stChDiscountProjMasterPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _stChDiscountProjMasterLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _stChDiscountProjMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _stChDiscountProjMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stChDiscountProjMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the st ch discount proj master with the primary key.
	*
	* @param stChDiscountProjMasterPK the primary key of the st ch discount proj master
	* @return the st ch discount proj master
	* @throws PortalException if a st ch discount proj master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.StChDiscountProjMaster getStChDiscountProjMaster(
		com.stpl.app.service.persistence.StChDiscountProjMasterPK stChDiscountProjMasterPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stChDiscountProjMasterLocalService.getStChDiscountProjMaster(stChDiscountProjMasterPK);
	}

	/**
	* Returns a range of all the st ch discount proj masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st ch discount proj masters
	* @param end the upper bound of the range of st ch discount proj masters (not inclusive)
	* @return the range of st ch discount proj masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.StChDiscountProjMaster> getStChDiscountProjMasters(
		int start, int end) {
		return _stChDiscountProjMasterLocalService.getStChDiscountProjMasters(start,
			end);
	}

	/**
	* Returns the number of st ch discount proj masters.
	*
	* @return the number of st ch discount proj masters
	*/
	@Override
	public int getStChDiscountProjMastersCount() {
		return _stChDiscountProjMasterLocalService.getStChDiscountProjMastersCount();
	}

	/**
	* Updates the st ch discount proj master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param stChDiscountProjMaster the st ch discount proj master
	* @return the st ch discount proj master that was updated
	*/
	@Override
	public com.stpl.app.model.StChDiscountProjMaster updateStChDiscountProjMaster(
		com.stpl.app.model.StChDiscountProjMaster stChDiscountProjMaster) {
		return _stChDiscountProjMasterLocalService.updateStChDiscountProjMaster(stChDiscountProjMaster);
	}

	@Override
	public StChDiscountProjMasterLocalService getWrappedService() {
		return _stChDiscountProjMasterLocalService;
	}

	@Override
	public void setWrappedService(
		StChDiscountProjMasterLocalService stChDiscountProjMasterLocalService) {
		_stChDiscountProjMasterLocalService = stChDiscountProjMasterLocalService;
	}

	private StChDiscountProjMasterLocalService _stChDiscountProjMasterLocalService;
}