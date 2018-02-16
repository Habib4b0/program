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
 * Provides a wrapper for {@link CfpContractDetailsLocalService}.
 *
 * @author
 * @see CfpContractDetailsLocalService
 * @generated
 */
@ProviderType
public class CfpContractDetailsLocalServiceWrapper
	implements CfpContractDetailsLocalService,
		ServiceWrapper<CfpContractDetailsLocalService> {
	public CfpContractDetailsLocalServiceWrapper(
		CfpContractDetailsLocalService cfpContractDetailsLocalService) {
		_cfpContractDetailsLocalService = cfpContractDetailsLocalService;
	}

	/**
	* Adds the cfp contract details to the database. Also notifies the appropriate model listeners.
	*
	* @param cfpContractDetails the cfp contract details
	* @return the cfp contract details that was added
	*/
	@Override
	public com.stpl.app.model.CfpContractDetails addCfpContractDetails(
		com.stpl.app.model.CfpContractDetails cfpContractDetails) {
		return _cfpContractDetailsLocalService.addCfpContractDetails(cfpContractDetails);
	}

	/**
	* Creates a new cfp contract details with the primary key. Does not add the cfp contract details to the database.
	*
	* @param cfpContractDetailsSid the primary key for the new cfp contract details
	* @return the new cfp contract details
	*/
	@Override
	public com.stpl.app.model.CfpContractDetails createCfpContractDetails(
		int cfpContractDetailsSid) {
		return _cfpContractDetailsLocalService.createCfpContractDetails(cfpContractDetailsSid);
	}

	/**
	* Deletes the cfp contract details from the database. Also notifies the appropriate model listeners.
	*
	* @param cfpContractDetails the cfp contract details
	* @return the cfp contract details that was removed
	*/
	@Override
	public com.stpl.app.model.CfpContractDetails deleteCfpContractDetails(
		com.stpl.app.model.CfpContractDetails cfpContractDetails) {
		return _cfpContractDetailsLocalService.deleteCfpContractDetails(cfpContractDetails);
	}

	/**
	* Deletes the cfp contract details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cfpContractDetailsSid the primary key of the cfp contract details
	* @return the cfp contract details that was removed
	* @throws PortalException if a cfp contract details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.CfpContractDetails deleteCfpContractDetails(
		int cfpContractDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cfpContractDetailsLocalService.deleteCfpContractDetails(cfpContractDetailsSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cfpContractDetailsLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _cfpContractDetailsLocalService.dynamicQuery();
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
		return _cfpContractDetailsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _cfpContractDetailsLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _cfpContractDetailsLocalService.dynamicQuery(dynamicQuery,
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
		return _cfpContractDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _cfpContractDetailsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.CfpContractDetails fetchCfpContractDetails(
		int cfpContractDetailsSid) {
		return _cfpContractDetailsLocalService.fetchCfpContractDetails(cfpContractDetailsSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _cfpContractDetailsLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the cfp contract details with the primary key.
	*
	* @param cfpContractDetailsSid the primary key of the cfp contract details
	* @return the cfp contract details
	* @throws PortalException if a cfp contract details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.CfpContractDetails getCfpContractDetails(
		int cfpContractDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cfpContractDetailsLocalService.getCfpContractDetails(cfpContractDetailsSid);
	}

	/**
	* Returns a range of all the cfp contract detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cfp contract detailses
	* @param end the upper bound of the range of cfp contract detailses (not inclusive)
	* @return the range of cfp contract detailses
	*/
	@Override
	public java.util.List<com.stpl.app.model.CfpContractDetails> getCfpContractDetailses(
		int start, int end) {
		return _cfpContractDetailsLocalService.getCfpContractDetailses(start,
			end);
	}

	/**
	* Returns the number of cfp contract detailses.
	*
	* @return the number of cfp contract detailses
	*/
	@Override
	public int getCfpContractDetailsesCount() {
		return _cfpContractDetailsLocalService.getCfpContractDetailsesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _cfpContractDetailsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _cfpContractDetailsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cfpContractDetailsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the cfp contract details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param cfpContractDetails the cfp contract details
	* @return the cfp contract details that was updated
	*/
	@Override
	public com.stpl.app.model.CfpContractDetails updateCfpContractDetails(
		com.stpl.app.model.CfpContractDetails cfpContractDetails) {
		return _cfpContractDetailsLocalService.updateCfpContractDetails(cfpContractDetails);
	}

	@Override
	public CfpContractDetailsLocalService getWrappedService() {
		return _cfpContractDetailsLocalService;
	}

	@Override
	public void setWrappedService(
		CfpContractDetailsLocalService cfpContractDetailsLocalService) {
		_cfpContractDetailsLocalService = cfpContractDetailsLocalService;
	}

	private CfpContractDetailsLocalService _cfpContractDetailsLocalService;
}