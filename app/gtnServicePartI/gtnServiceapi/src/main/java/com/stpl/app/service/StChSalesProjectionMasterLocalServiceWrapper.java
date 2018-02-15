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
 * Provides a wrapper for {@link StChSalesProjectionMasterLocalService}.
 *
 * @author
 * @see StChSalesProjectionMasterLocalService
 * @generated
 */
@ProviderType
public class StChSalesProjectionMasterLocalServiceWrapper
	implements StChSalesProjectionMasterLocalService,
		ServiceWrapper<StChSalesProjectionMasterLocalService> {
	public StChSalesProjectionMasterLocalServiceWrapper(
		StChSalesProjectionMasterLocalService stChSalesProjectionMasterLocalService) {
		_stChSalesProjectionMasterLocalService = stChSalesProjectionMasterLocalService;
	}

	/**
	* Adds the st ch sales projection master to the database. Also notifies the appropriate model listeners.
	*
	* @param stChSalesProjectionMaster the st ch sales projection master
	* @return the st ch sales projection master that was added
	*/
	@Override
	public com.stpl.app.model.StChSalesProjectionMaster addStChSalesProjectionMaster(
		com.stpl.app.model.StChSalesProjectionMaster stChSalesProjectionMaster) {
		return _stChSalesProjectionMasterLocalService.addStChSalesProjectionMaster(stChSalesProjectionMaster);
	}

	/**
	* Creates a new st ch sales projection master with the primary key. Does not add the st ch sales projection master to the database.
	*
	* @param stChSalesProjectionMasterPK the primary key for the new st ch sales projection master
	* @return the new st ch sales projection master
	*/
	@Override
	public com.stpl.app.model.StChSalesProjectionMaster createStChSalesProjectionMaster(
		com.stpl.app.service.persistence.StChSalesProjectionMasterPK stChSalesProjectionMasterPK) {
		return _stChSalesProjectionMasterLocalService.createStChSalesProjectionMaster(stChSalesProjectionMasterPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stChSalesProjectionMasterLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the st ch sales projection master from the database. Also notifies the appropriate model listeners.
	*
	* @param stChSalesProjectionMaster the st ch sales projection master
	* @return the st ch sales projection master that was removed
	*/
	@Override
	public com.stpl.app.model.StChSalesProjectionMaster deleteStChSalesProjectionMaster(
		com.stpl.app.model.StChSalesProjectionMaster stChSalesProjectionMaster) {
		return _stChSalesProjectionMasterLocalService.deleteStChSalesProjectionMaster(stChSalesProjectionMaster);
	}

	/**
	* Deletes the st ch sales projection master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stChSalesProjectionMasterPK the primary key of the st ch sales projection master
	* @return the st ch sales projection master that was removed
	* @throws PortalException if a st ch sales projection master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.StChSalesProjectionMaster deleteStChSalesProjectionMaster(
		com.stpl.app.service.persistence.StChSalesProjectionMasterPK stChSalesProjectionMasterPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stChSalesProjectionMasterLocalService.deleteStChSalesProjectionMaster(stChSalesProjectionMasterPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _stChSalesProjectionMasterLocalService.dynamicQuery();
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
		return _stChSalesProjectionMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stChSalesProjectionMasterLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stChSalesProjectionMasterLocalService.dynamicQuery(dynamicQuery,
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
		return _stChSalesProjectionMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _stChSalesProjectionMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.StChSalesProjectionMaster fetchStChSalesProjectionMaster(
		com.stpl.app.service.persistence.StChSalesProjectionMasterPK stChSalesProjectionMasterPK) {
		return _stChSalesProjectionMasterLocalService.fetchStChSalesProjectionMaster(stChSalesProjectionMasterPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _stChSalesProjectionMasterLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _stChSalesProjectionMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _stChSalesProjectionMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stChSalesProjectionMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the st ch sales projection master with the primary key.
	*
	* @param stChSalesProjectionMasterPK the primary key of the st ch sales projection master
	* @return the st ch sales projection master
	* @throws PortalException if a st ch sales projection master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.StChSalesProjectionMaster getStChSalesProjectionMaster(
		com.stpl.app.service.persistence.StChSalesProjectionMasterPK stChSalesProjectionMasterPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stChSalesProjectionMasterLocalService.getStChSalesProjectionMaster(stChSalesProjectionMasterPK);
	}

	/**
	* Returns a range of all the st ch sales projection masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st ch sales projection masters
	* @param end the upper bound of the range of st ch sales projection masters (not inclusive)
	* @return the range of st ch sales projection masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.StChSalesProjectionMaster> getStChSalesProjectionMasters(
		int start, int end) {
		return _stChSalesProjectionMasterLocalService.getStChSalesProjectionMasters(start,
			end);
	}

	/**
	* Returns the number of st ch sales projection masters.
	*
	* @return the number of st ch sales projection masters
	*/
	@Override
	public int getStChSalesProjectionMastersCount() {
		return _stChSalesProjectionMasterLocalService.getStChSalesProjectionMastersCount();
	}

	/**
	* Updates the st ch sales projection master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param stChSalesProjectionMaster the st ch sales projection master
	* @return the st ch sales projection master that was updated
	*/
	@Override
	public com.stpl.app.model.StChSalesProjectionMaster updateStChSalesProjectionMaster(
		com.stpl.app.model.StChSalesProjectionMaster stChSalesProjectionMaster) {
		return _stChSalesProjectionMasterLocalService.updateStChSalesProjectionMaster(stChSalesProjectionMaster);
	}

	@Override
	public StChSalesProjectionMasterLocalService getWrappedService() {
		return _stChSalesProjectionMasterLocalService;
	}

	@Override
	public void setWrappedService(
		StChSalesProjectionMasterLocalService stChSalesProjectionMasterLocalService) {
		_stChSalesProjectionMasterLocalService = stChSalesProjectionMasterLocalService;
	}

	private StChSalesProjectionMasterLocalService _stChSalesProjectionMasterLocalService;
}