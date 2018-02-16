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
 * Provides a wrapper for {@link PsContractLocalService}.
 *
 * @author
 * @see PsContractLocalService
 * @generated
 */
@ProviderType
public class PsContractLocalServiceWrapper implements PsContractLocalService,
	ServiceWrapper<PsContractLocalService> {
	public PsContractLocalServiceWrapper(
		PsContractLocalService psContractLocalService) {
		_psContractLocalService = psContractLocalService;
	}

	/**
	* Adds the ps contract to the database. Also notifies the appropriate model listeners.
	*
	* @param psContract the ps contract
	* @return the ps contract that was added
	*/
	@Override
	public com.stpl.app.model.PsContract addPsContract(
		com.stpl.app.model.PsContract psContract) {
		return _psContractLocalService.addPsContract(psContract);
	}

	/**
	* Creates a new ps contract with the primary key. Does not add the ps contract to the database.
	*
	* @param psContractSid the primary key for the new ps contract
	* @return the new ps contract
	*/
	@Override
	public com.stpl.app.model.PsContract createPsContract(int psContractSid) {
		return _psContractLocalService.createPsContract(psContractSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _psContractLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the ps contract with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param psContractSid the primary key of the ps contract
	* @return the ps contract that was removed
	* @throws PortalException if a ps contract with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.PsContract deletePsContract(int psContractSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _psContractLocalService.deletePsContract(psContractSid);
	}

	/**
	* Deletes the ps contract from the database. Also notifies the appropriate model listeners.
	*
	* @param psContract the ps contract
	* @return the ps contract that was removed
	*/
	@Override
	public com.stpl.app.model.PsContract deletePsContract(
		com.stpl.app.model.PsContract psContract) {
		return _psContractLocalService.deletePsContract(psContract);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _psContractLocalService.dynamicQuery();
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
		return _psContractLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _psContractLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _psContractLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _psContractLocalService.dynamicQueryCount(dynamicQuery);
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
		return _psContractLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.PsContract fetchPsContract(int psContractSid) {
		return _psContractLocalService.fetchPsContract(psContractSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _psContractLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _psContractLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _psContractLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _psContractLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the ps contract with the primary key.
	*
	* @param psContractSid the primary key of the ps contract
	* @return the ps contract
	* @throws PortalException if a ps contract with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.PsContract getPsContract(int psContractSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _psContractLocalService.getPsContract(psContractSid);
	}

	/**
	* Returns a range of all the ps contracts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ps contracts
	* @param end the upper bound of the range of ps contracts (not inclusive)
	* @return the range of ps contracts
	*/
	@Override
	public java.util.List<com.stpl.app.model.PsContract> getPsContracts(
		int start, int end) {
		return _psContractLocalService.getPsContracts(start, end);
	}

	/**
	* Returns the number of ps contracts.
	*
	* @return the number of ps contracts
	*/
	@Override
	public int getPsContractsCount() {
		return _psContractLocalService.getPsContractsCount();
	}

	/**
	* Updates the ps contract in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param psContract the ps contract
	* @return the ps contract that was updated
	*/
	@Override
	public com.stpl.app.model.PsContract updatePsContract(
		com.stpl.app.model.PsContract psContract) {
		return _psContractLocalService.updatePsContract(psContract);
	}

	@Override
	public PsContractLocalService getWrappedService() {
		return _psContractLocalService;
	}

	@Override
	public void setWrappedService(PsContractLocalService psContractLocalService) {
		_psContractLocalService = psContractLocalService;
	}

	private PsContractLocalService _psContractLocalService;
}