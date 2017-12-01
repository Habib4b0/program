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
 * Provides a wrapper for {@link ContractMasterLocalService}.
 *
 * @author
 * @see ContractMasterLocalService
 * @generated
 */
@ProviderType
public class ContractMasterLocalServiceWrapper
	implements ContractMasterLocalService,
		ServiceWrapper<ContractMasterLocalService> {
	public ContractMasterLocalServiceWrapper(
		ContractMasterLocalService contractMasterLocalService) {
		_contractMasterLocalService = contractMasterLocalService;
	}

	/**
	* Adds the contract master to the database. Also notifies the appropriate model listeners.
	*
	* @param contractMaster the contract master
	* @return the contract master that was added
	*/
	@Override
	public com.stpl.app.model.ContractMaster addContractMaster(
		com.stpl.app.model.ContractMaster contractMaster) {
		return _contractMasterLocalService.addContractMaster(contractMaster);
	}

	/**
	* Creates a new contract master with the primary key. Does not add the contract master to the database.
	*
	* @param contractMasterSid the primary key for the new contract master
	* @return the new contract master
	*/
	@Override
	public com.stpl.app.model.ContractMaster createContractMaster(
		int contractMasterSid) {
		return _contractMasterLocalService.createContractMaster(contractMasterSid);
	}

	/**
	* Deletes the contract master from the database. Also notifies the appropriate model listeners.
	*
	* @param contractMaster the contract master
	* @return the contract master that was removed
	*/
	@Override
	public com.stpl.app.model.ContractMaster deleteContractMaster(
		com.stpl.app.model.ContractMaster contractMaster) {
		return _contractMasterLocalService.deleteContractMaster(contractMaster);
	}

	/**
	* Deletes the contract master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contractMasterSid the primary key of the contract master
	* @return the contract master that was removed
	* @throws PortalException if a contract master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ContractMaster deleteContractMaster(
		int contractMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contractMasterLocalService.deleteContractMaster(contractMasterSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contractMasterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _contractMasterLocalService.dynamicQuery();
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
		return _contractMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ContractMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _contractMasterLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ContractMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _contractMasterLocalService.dynamicQuery(dynamicQuery, start,
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
		return _contractMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _contractMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.ContractMaster fetchContractMaster(
		int contractMasterSid) {
		return _contractMasterLocalService.fetchContractMaster(contractMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _contractMasterLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the contract master with the primary key.
	*
	* @param contractMasterSid the primary key of the contract master
	* @return the contract master
	* @throws PortalException if a contract master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ContractMaster getContractMaster(
		int contractMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contractMasterLocalService.getContractMaster(contractMasterSid);
	}

	/**
	* Returns a range of all the contract masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ContractMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of contract masters
	* @param end the upper bound of the range of contract masters (not inclusive)
	* @return the range of contract masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.ContractMaster> getContractMasters(
		int start, int end) {
		return _contractMasterLocalService.getContractMasters(start, end);
	}

	/**
	* Returns the number of contract masters.
	*
	* @return the number of contract masters
	*/
	@Override
	public int getContractMastersCount() {
		return _contractMasterLocalService.getContractMastersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _contractMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _contractMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contractMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the contract master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param contractMaster the contract master
	* @return the contract master that was updated
	*/
	@Override
	public com.stpl.app.model.ContractMaster updateContractMaster(
		com.stpl.app.model.ContractMaster contractMaster) {
		return _contractMasterLocalService.updateContractMaster(contractMaster);
	}

	@Override
	public ContractMasterLocalService getWrappedService() {
		return _contractMasterLocalService;
	}

	@Override
	public void setWrappedService(
		ContractMasterLocalService contractMasterLocalService) {
		_contractMasterLocalService = contractMasterLocalService;
	}

	private ContractMasterLocalService _contractMasterLocalService;
}