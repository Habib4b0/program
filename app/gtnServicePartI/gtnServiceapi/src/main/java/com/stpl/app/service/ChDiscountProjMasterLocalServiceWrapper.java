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
 * Provides a wrapper for {@link ChDiscountProjMasterLocalService}.
 *
 * @author
 * @see ChDiscountProjMasterLocalService
 * @generated
 */
@ProviderType
public class ChDiscountProjMasterLocalServiceWrapper
	implements ChDiscountProjMasterLocalService,
		ServiceWrapper<ChDiscountProjMasterLocalService> {
	public ChDiscountProjMasterLocalServiceWrapper(
		ChDiscountProjMasterLocalService chDiscountProjMasterLocalService) {
		_chDiscountProjMasterLocalService = chDiscountProjMasterLocalService;
	}

	/**
	* Adds the ch discount proj master to the database. Also notifies the appropriate model listeners.
	*
	* @param chDiscountProjMaster the ch discount proj master
	* @return the ch discount proj master that was added
	*/
	@Override
	public com.stpl.app.model.ChDiscountProjMaster addChDiscountProjMaster(
		com.stpl.app.model.ChDiscountProjMaster chDiscountProjMaster) {
		return _chDiscountProjMasterLocalService.addChDiscountProjMaster(chDiscountProjMaster);
	}

	/**
	* Creates a new ch discount proj master with the primary key. Does not add the ch discount proj master to the database.
	*
	* @param chDiscountProjMasterPK the primary key for the new ch discount proj master
	* @return the new ch discount proj master
	*/
	@Override
	public com.stpl.app.model.ChDiscountProjMaster createChDiscountProjMaster(
		com.stpl.app.service.persistence.ChDiscountProjMasterPK chDiscountProjMasterPK) {
		return _chDiscountProjMasterLocalService.createChDiscountProjMaster(chDiscountProjMasterPK);
	}

	/**
	* Deletes the ch discount proj master from the database. Also notifies the appropriate model listeners.
	*
	* @param chDiscountProjMaster the ch discount proj master
	* @return the ch discount proj master that was removed
	*/
	@Override
	public com.stpl.app.model.ChDiscountProjMaster deleteChDiscountProjMaster(
		com.stpl.app.model.ChDiscountProjMaster chDiscountProjMaster) {
		return _chDiscountProjMasterLocalService.deleteChDiscountProjMaster(chDiscountProjMaster);
	}

	/**
	* Deletes the ch discount proj master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param chDiscountProjMasterPK the primary key of the ch discount proj master
	* @return the ch discount proj master that was removed
	* @throws PortalException if a ch discount proj master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ChDiscountProjMaster deleteChDiscountProjMaster(
		com.stpl.app.service.persistence.ChDiscountProjMasterPK chDiscountProjMasterPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _chDiscountProjMasterLocalService.deleteChDiscountProjMaster(chDiscountProjMasterPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _chDiscountProjMasterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _chDiscountProjMasterLocalService.dynamicQuery();
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
		return _chDiscountProjMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _chDiscountProjMasterLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _chDiscountProjMasterLocalService.dynamicQuery(dynamicQuery,
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
		return _chDiscountProjMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _chDiscountProjMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.ChDiscountProjMaster fetchChDiscountProjMaster(
		com.stpl.app.service.persistence.ChDiscountProjMasterPK chDiscountProjMasterPK) {
		return _chDiscountProjMasterLocalService.fetchChDiscountProjMaster(chDiscountProjMasterPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _chDiscountProjMasterLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the ch discount proj master with the primary key.
	*
	* @param chDiscountProjMasterPK the primary key of the ch discount proj master
	* @return the ch discount proj master
	* @throws PortalException if a ch discount proj master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ChDiscountProjMaster getChDiscountProjMaster(
		com.stpl.app.service.persistence.ChDiscountProjMasterPK chDiscountProjMasterPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _chDiscountProjMasterLocalService.getChDiscountProjMaster(chDiscountProjMasterPK);
	}

	/**
	* Returns a range of all the ch discount proj masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch discount proj masters
	* @param end the upper bound of the range of ch discount proj masters (not inclusive)
	* @return the range of ch discount proj masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.ChDiscountProjMaster> getChDiscountProjMasters(
		int start, int end) {
		return _chDiscountProjMasterLocalService.getChDiscountProjMasters(start,
			end);
	}

	/**
	* Returns the number of ch discount proj masters.
	*
	* @return the number of ch discount proj masters
	*/
	@Override
	public int getChDiscountProjMastersCount() {
		return _chDiscountProjMasterLocalService.getChDiscountProjMastersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _chDiscountProjMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _chDiscountProjMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _chDiscountProjMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the ch discount proj master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param chDiscountProjMaster the ch discount proj master
	* @return the ch discount proj master that was updated
	*/
	@Override
	public com.stpl.app.model.ChDiscountProjMaster updateChDiscountProjMaster(
		com.stpl.app.model.ChDiscountProjMaster chDiscountProjMaster) {
		return _chDiscountProjMasterLocalService.updateChDiscountProjMaster(chDiscountProjMaster);
	}

	@Override
	public ChDiscountProjMasterLocalService getWrappedService() {
		return _chDiscountProjMasterLocalService;
	}

	@Override
	public void setWrappedService(
		ChDiscountProjMasterLocalService chDiscountProjMasterLocalService) {
		_chDiscountProjMasterLocalService = chDiscountProjMasterLocalService;
	}

	private ChDiscountProjMasterLocalService _chDiscountProjMasterLocalService;
}