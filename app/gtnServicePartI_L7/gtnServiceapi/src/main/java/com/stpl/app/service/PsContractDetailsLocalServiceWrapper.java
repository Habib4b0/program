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
 * Provides a wrapper for {@link PsContractDetailsLocalService}.
 *
 * @author
 * @see PsContractDetailsLocalService
 * @generated
 */
@ProviderType
public class PsContractDetailsLocalServiceWrapper
	implements PsContractDetailsLocalService,
		ServiceWrapper<PsContractDetailsLocalService> {
	public PsContractDetailsLocalServiceWrapper(
		PsContractDetailsLocalService psContractDetailsLocalService) {
		_psContractDetailsLocalService = psContractDetailsLocalService;
	}

	/**
	* Adds the ps contract details to the database. Also notifies the appropriate model listeners.
	*
	* @param psContractDetails the ps contract details
	* @return the ps contract details that was added
	*/
	@Override
	public com.stpl.app.model.PsContractDetails addPsContractDetails(
		com.stpl.app.model.PsContractDetails psContractDetails) {
		return _psContractDetailsLocalService.addPsContractDetails(psContractDetails);
	}

	/**
	* Creates a new ps contract details with the primary key. Does not add the ps contract details to the database.
	*
	* @param psContractDetailsSid the primary key for the new ps contract details
	* @return the new ps contract details
	*/
	@Override
	public com.stpl.app.model.PsContractDetails createPsContractDetails(
		int psContractDetailsSid) {
		return _psContractDetailsLocalService.createPsContractDetails(psContractDetailsSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _psContractDetailsLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the ps contract details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param psContractDetailsSid the primary key of the ps contract details
	* @return the ps contract details that was removed
	* @throws PortalException if a ps contract details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.PsContractDetails deletePsContractDetails(
		int psContractDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _psContractDetailsLocalService.deletePsContractDetails(psContractDetailsSid);
	}

	/**
	* Deletes the ps contract details from the database. Also notifies the appropriate model listeners.
	*
	* @param psContractDetails the ps contract details
	* @return the ps contract details that was removed
	*/
	@Override
	public com.stpl.app.model.PsContractDetails deletePsContractDetails(
		com.stpl.app.model.PsContractDetails psContractDetails) {
		return _psContractDetailsLocalService.deletePsContractDetails(psContractDetails);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _psContractDetailsLocalService.dynamicQuery();
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
		return _psContractDetailsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _psContractDetailsLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _psContractDetailsLocalService.dynamicQuery(dynamicQuery, start,
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
		return _psContractDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _psContractDetailsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.PsContractDetails fetchPsContractDetails(
		int psContractDetailsSid) {
		return _psContractDetailsLocalService.fetchPsContractDetails(psContractDetailsSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _psContractDetailsLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _psContractDetailsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _psContractDetailsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _psContractDetailsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the ps contract details with the primary key.
	*
	* @param psContractDetailsSid the primary key of the ps contract details
	* @return the ps contract details
	* @throws PortalException if a ps contract details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.PsContractDetails getPsContractDetails(
		int psContractDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _psContractDetailsLocalService.getPsContractDetails(psContractDetailsSid);
	}

	/**
	* Returns a range of all the ps contract detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ps contract detailses
	* @param end the upper bound of the range of ps contract detailses (not inclusive)
	* @return the range of ps contract detailses
	*/
	@Override
	public java.util.List<com.stpl.app.model.PsContractDetails> getPsContractDetailses(
		int start, int end) {
		return _psContractDetailsLocalService.getPsContractDetailses(start, end);
	}

	/**
	* Returns the number of ps contract detailses.
	*
	* @return the number of ps contract detailses
	*/
	@Override
	public int getPsContractDetailsesCount() {
		return _psContractDetailsLocalService.getPsContractDetailsesCount();
	}

	/**
	* Updates the ps contract details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param psContractDetails the ps contract details
	* @return the ps contract details that was updated
	*/
	@Override
	public com.stpl.app.model.PsContractDetails updatePsContractDetails(
		com.stpl.app.model.PsContractDetails psContractDetails) {
		return _psContractDetailsLocalService.updatePsContractDetails(psContractDetails);
	}

	@Override
	public PsContractDetailsLocalService getWrappedService() {
		return _psContractDetailsLocalService;
	}

	@Override
	public void setWrappedService(
		PsContractDetailsLocalService psContractDetailsLocalService) {
		_psContractDetailsLocalService = psContractDetailsLocalService;
	}

	private PsContractDetailsLocalService _psContractDetailsLocalService;
}