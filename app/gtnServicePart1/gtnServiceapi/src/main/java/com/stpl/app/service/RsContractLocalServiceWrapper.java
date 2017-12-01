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
 * Provides a wrapper for {@link RsContractLocalService}.
 *
 * @author
 * @see RsContractLocalService
 * @generated
 */
@ProviderType
public class RsContractLocalServiceWrapper implements RsContractLocalService,
	ServiceWrapper<RsContractLocalService> {
	public RsContractLocalServiceWrapper(
		RsContractLocalService rsContractLocalService) {
		_rsContractLocalService = rsContractLocalService;
	}

	/**
	* Adds the rs contract to the database. Also notifies the appropriate model listeners.
	*
	* @param rsContract the rs contract
	* @return the rs contract that was added
	*/
	@Override
	public com.stpl.app.model.RsContract addRsContract(
		com.stpl.app.model.RsContract rsContract) {
		return _rsContractLocalService.addRsContract(rsContract);
	}

	/**
	* Creates a new rs contract with the primary key. Does not add the rs contract to the database.
	*
	* @param rsContractSid the primary key for the new rs contract
	* @return the new rs contract
	*/
	@Override
	public com.stpl.app.model.RsContract createRsContract(int rsContractSid) {
		return _rsContractLocalService.createRsContract(rsContractSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _rsContractLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the rs contract with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param rsContractSid the primary key of the rs contract
	* @return the rs contract that was removed
	* @throws PortalException if a rs contract with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.RsContract deleteRsContract(int rsContractSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _rsContractLocalService.deleteRsContract(rsContractSid);
	}

	/**
	* Deletes the rs contract from the database. Also notifies the appropriate model listeners.
	*
	* @param rsContract the rs contract
	* @return the rs contract that was removed
	*/
	@Override
	public com.stpl.app.model.RsContract deleteRsContract(
		com.stpl.app.model.RsContract rsContract) {
		return _rsContractLocalService.deleteRsContract(rsContract);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _rsContractLocalService.dynamicQuery();
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
		return _rsContractLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _rsContractLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _rsContractLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _rsContractLocalService.dynamicQueryCount(dynamicQuery);
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
		return _rsContractLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.RsContract fetchRsContract(int rsContractSid) {
		return _rsContractLocalService.fetchRsContract(rsContractSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _rsContractLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _rsContractLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _rsContractLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _rsContractLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the rs contract with the primary key.
	*
	* @param rsContractSid the primary key of the rs contract
	* @return the rs contract
	* @throws PortalException if a rs contract with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.RsContract getRsContract(int rsContractSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _rsContractLocalService.getRsContract(rsContractSid);
	}

	/**
	* Returns a range of all the rs contracts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rs contracts
	* @param end the upper bound of the range of rs contracts (not inclusive)
	* @return the range of rs contracts
	*/
	@Override
	public java.util.List<com.stpl.app.model.RsContract> getRsContracts(
		int start, int end) {
		return _rsContractLocalService.getRsContracts(start, end);
	}

	/**
	* Returns the number of rs contracts.
	*
	* @return the number of rs contracts
	*/
	@Override
	public int getRsContractsCount() {
		return _rsContractLocalService.getRsContractsCount();
	}

	/**
	* Updates the rs contract in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param rsContract the rs contract
	* @return the rs contract that was updated
	*/
	@Override
	public com.stpl.app.model.RsContract updateRsContract(
		com.stpl.app.model.RsContract rsContract) {
		return _rsContractLocalService.updateRsContract(rsContract);
	}

	@Override
	public RsContractLocalService getWrappedService() {
		return _rsContractLocalService;
	}

	@Override
	public void setWrappedService(RsContractLocalService rsContractLocalService) {
		_rsContractLocalService = rsContractLocalService;
	}

	private RsContractLocalService _rsContractLocalService;
}