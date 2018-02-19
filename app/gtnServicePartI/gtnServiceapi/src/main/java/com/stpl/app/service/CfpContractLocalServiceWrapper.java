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
 * Provides a wrapper for {@link CfpContractLocalService}.
 *
 * @author
 * @see CfpContractLocalService
 * @generated
 */
@ProviderType
public class CfpContractLocalServiceWrapper implements CfpContractLocalService,
	ServiceWrapper<CfpContractLocalService> {
	public CfpContractLocalServiceWrapper(
		CfpContractLocalService cfpContractLocalService) {
		_cfpContractLocalService = cfpContractLocalService;
	}

	/**
	* Adds the cfp contract to the database. Also notifies the appropriate model listeners.
	*
	* @param cfpContract the cfp contract
	* @return the cfp contract that was added
	*/
	@Override
	public com.stpl.app.model.CfpContract addCfpContract(
		com.stpl.app.model.CfpContract cfpContract) {
		return _cfpContractLocalService.addCfpContract(cfpContract);
	}

	/**
	* Creates a new cfp contract with the primary key. Does not add the cfp contract to the database.
	*
	* @param cfpContractSid the primary key for the new cfp contract
	* @return the new cfp contract
	*/
	@Override
	public com.stpl.app.model.CfpContract createCfpContract(int cfpContractSid) {
		return _cfpContractLocalService.createCfpContract(cfpContractSid);
	}

	/**
	* Deletes the cfp contract from the database. Also notifies the appropriate model listeners.
	*
	* @param cfpContract the cfp contract
	* @return the cfp contract that was removed
	*/
	@Override
	public com.stpl.app.model.CfpContract deleteCfpContract(
		com.stpl.app.model.CfpContract cfpContract) {
		return _cfpContractLocalService.deleteCfpContract(cfpContract);
	}

	/**
	* Deletes the cfp contract with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cfpContractSid the primary key of the cfp contract
	* @return the cfp contract that was removed
	* @throws PortalException if a cfp contract with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.CfpContract deleteCfpContract(int cfpContractSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cfpContractLocalService.deleteCfpContract(cfpContractSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cfpContractLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _cfpContractLocalService.dynamicQuery();
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
		return _cfpContractLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _cfpContractLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _cfpContractLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _cfpContractLocalService.dynamicQueryCount(dynamicQuery);
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
		return _cfpContractLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.CfpContract fetchCfpContract(int cfpContractSid) {
		return _cfpContractLocalService.fetchCfpContract(cfpContractSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _cfpContractLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the cfp contract with the primary key.
	*
	* @param cfpContractSid the primary key of the cfp contract
	* @return the cfp contract
	* @throws PortalException if a cfp contract with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.CfpContract getCfpContract(int cfpContractSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cfpContractLocalService.getCfpContract(cfpContractSid);
	}

	/**
	* Returns a range of all the cfp contracts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cfp contracts
	* @param end the upper bound of the range of cfp contracts (not inclusive)
	* @return the range of cfp contracts
	*/
	@Override
	public java.util.List<com.stpl.app.model.CfpContract> getCfpContracts(
		int start, int end) {
		return _cfpContractLocalService.getCfpContracts(start, end);
	}

	/**
	* Returns the number of cfp contracts.
	*
	* @return the number of cfp contracts
	*/
	@Override
	public int getCfpContractsCount() {
		return _cfpContractLocalService.getCfpContractsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _cfpContractLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _cfpContractLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cfpContractLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the cfp contract in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param cfpContract the cfp contract
	* @return the cfp contract that was updated
	*/
	@Override
	public com.stpl.app.model.CfpContract updateCfpContract(
		com.stpl.app.model.CfpContract cfpContract) {
		return _cfpContractLocalService.updateCfpContract(cfpContract);
	}

	@Override
	public CfpContractLocalService getWrappedService() {
		return _cfpContractLocalService;
	}

	@Override
	public void setWrappedService(
		CfpContractLocalService cfpContractLocalService) {
		_cfpContractLocalService = cfpContractLocalService;
	}

	private CfpContractLocalService _cfpContractLocalService;
}