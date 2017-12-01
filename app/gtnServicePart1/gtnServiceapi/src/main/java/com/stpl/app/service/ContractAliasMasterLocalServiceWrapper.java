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
 * Provides a wrapper for {@link ContractAliasMasterLocalService}.
 *
 * @author
 * @see ContractAliasMasterLocalService
 * @generated
 */
@ProviderType
public class ContractAliasMasterLocalServiceWrapper
	implements ContractAliasMasterLocalService,
		ServiceWrapper<ContractAliasMasterLocalService> {
	public ContractAliasMasterLocalServiceWrapper(
		ContractAliasMasterLocalService contractAliasMasterLocalService) {
		_contractAliasMasterLocalService = contractAliasMasterLocalService;
	}

	/**
	* Adds the contract alias master to the database. Also notifies the appropriate model listeners.
	*
	* @param contractAliasMaster the contract alias master
	* @return the contract alias master that was added
	*/
	@Override
	public com.stpl.app.model.ContractAliasMaster addContractAliasMaster(
		com.stpl.app.model.ContractAliasMaster contractAliasMaster) {
		return _contractAliasMasterLocalService.addContractAliasMaster(contractAliasMaster);
	}

	/**
	* Creates a new contract alias master with the primary key. Does not add the contract alias master to the database.
	*
	* @param contractAliasMasterSid the primary key for the new contract alias master
	* @return the new contract alias master
	*/
	@Override
	public com.stpl.app.model.ContractAliasMaster createContractAliasMaster(
		int contractAliasMasterSid) {
		return _contractAliasMasterLocalService.createContractAliasMaster(contractAliasMasterSid);
	}

	/**
	* Deletes the contract alias master from the database. Also notifies the appropriate model listeners.
	*
	* @param contractAliasMaster the contract alias master
	* @return the contract alias master that was removed
	*/
	@Override
	public com.stpl.app.model.ContractAliasMaster deleteContractAliasMaster(
		com.stpl.app.model.ContractAliasMaster contractAliasMaster) {
		return _contractAliasMasterLocalService.deleteContractAliasMaster(contractAliasMaster);
	}

	/**
	* Deletes the contract alias master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contractAliasMasterSid the primary key of the contract alias master
	* @return the contract alias master that was removed
	* @throws PortalException if a contract alias master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ContractAliasMaster deleteContractAliasMaster(
		int contractAliasMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contractAliasMasterLocalService.deleteContractAliasMaster(contractAliasMasterSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contractAliasMasterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _contractAliasMasterLocalService.dynamicQuery();
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
		return _contractAliasMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ContractAliasMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _contractAliasMasterLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ContractAliasMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _contractAliasMasterLocalService.dynamicQuery(dynamicQuery,
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
		return _contractAliasMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _contractAliasMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.ContractAliasMaster fetchContractAliasMaster(
		int contractAliasMasterSid) {
		return _contractAliasMasterLocalService.fetchContractAliasMaster(contractAliasMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _contractAliasMasterLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the contract alias master with the primary key.
	*
	* @param contractAliasMasterSid the primary key of the contract alias master
	* @return the contract alias master
	* @throws PortalException if a contract alias master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ContractAliasMaster getContractAliasMaster(
		int contractAliasMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contractAliasMasterLocalService.getContractAliasMaster(contractAliasMasterSid);
	}

	/**
	* Returns a range of all the contract alias masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ContractAliasMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of contract alias masters
	* @param end the upper bound of the range of contract alias masters (not inclusive)
	* @return the range of contract alias masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.ContractAliasMaster> getContractAliasMasters(
		int start, int end) {
		return _contractAliasMasterLocalService.getContractAliasMasters(start,
			end);
	}

	/**
	* Returns the number of contract alias masters.
	*
	* @return the number of contract alias masters
	*/
	@Override
	public int getContractAliasMastersCount() {
		return _contractAliasMasterLocalService.getContractAliasMastersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _contractAliasMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _contractAliasMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contractAliasMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the contract alias master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param contractAliasMaster the contract alias master
	* @return the contract alias master that was updated
	*/
	@Override
	public com.stpl.app.model.ContractAliasMaster updateContractAliasMaster(
		com.stpl.app.model.ContractAliasMaster contractAliasMaster) {
		return _contractAliasMasterLocalService.updateContractAliasMaster(contractAliasMaster);
	}

	@Override
	public ContractAliasMasterLocalService getWrappedService() {
		return _contractAliasMasterLocalService;
	}

	@Override
	public void setWrappedService(
		ContractAliasMasterLocalService contractAliasMasterLocalService) {
		_contractAliasMasterLocalService = contractAliasMasterLocalService;
	}

	private ContractAliasMasterLocalService _contractAliasMasterLocalService;
}