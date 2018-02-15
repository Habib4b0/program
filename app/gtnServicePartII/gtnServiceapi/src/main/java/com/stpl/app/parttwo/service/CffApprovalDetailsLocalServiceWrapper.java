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

package com.stpl.app.parttwo.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CffApprovalDetailsLocalService}.
 *
 * @author
 * @see CffApprovalDetailsLocalService
 * @generated
 */
@ProviderType
public class CffApprovalDetailsLocalServiceWrapper
	implements CffApprovalDetailsLocalService,
		ServiceWrapper<CffApprovalDetailsLocalService> {
	public CffApprovalDetailsLocalServiceWrapper(
		CffApprovalDetailsLocalService cffApprovalDetailsLocalService) {
		_cffApprovalDetailsLocalService = cffApprovalDetailsLocalService;
	}

	/**
	* Adds the cff approval details to the database. Also notifies the appropriate model listeners.
	*
	* @param cffApprovalDetails the cff approval details
	* @return the cff approval details that was added
	*/
	@Override
	public com.stpl.app.parttwo.model.CffApprovalDetails addCffApprovalDetails(
		com.stpl.app.parttwo.model.CffApprovalDetails cffApprovalDetails) {
		return _cffApprovalDetailsLocalService.addCffApprovalDetails(cffApprovalDetails);
	}

	/**
	* Creates a new cff approval details with the primary key. Does not add the cff approval details to the database.
	*
	* @param cffApprovalDetailsSid the primary key for the new cff approval details
	* @return the new cff approval details
	*/
	@Override
	public com.stpl.app.parttwo.model.CffApprovalDetails createCffApprovalDetails(
		int cffApprovalDetailsSid) {
		return _cffApprovalDetailsLocalService.createCffApprovalDetails(cffApprovalDetailsSid);
	}

	/**
	* Deletes the cff approval details from the database. Also notifies the appropriate model listeners.
	*
	* @param cffApprovalDetails the cff approval details
	* @return the cff approval details that was removed
	*/
	@Override
	public com.stpl.app.parttwo.model.CffApprovalDetails deleteCffApprovalDetails(
		com.stpl.app.parttwo.model.CffApprovalDetails cffApprovalDetails) {
		return _cffApprovalDetailsLocalService.deleteCffApprovalDetails(cffApprovalDetails);
	}

	/**
	* Deletes the cff approval details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cffApprovalDetailsSid the primary key of the cff approval details
	* @return the cff approval details that was removed
	* @throws PortalException if a cff approval details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.CffApprovalDetails deleteCffApprovalDetails(
		int cffApprovalDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cffApprovalDetailsLocalService.deleteCffApprovalDetails(cffApprovalDetailsSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cffApprovalDetailsLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _cffApprovalDetailsLocalService.dynamicQuery();
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
		return _cffApprovalDetailsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffApprovalDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _cffApprovalDetailsLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffApprovalDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _cffApprovalDetailsLocalService.dynamicQuery(dynamicQuery,
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
		return _cffApprovalDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _cffApprovalDetailsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.parttwo.model.CffApprovalDetails fetchCffApprovalDetails(
		int cffApprovalDetailsSid) {
		return _cffApprovalDetailsLocalService.fetchCffApprovalDetails(cffApprovalDetailsSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _cffApprovalDetailsLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the cff approval details with the primary key.
	*
	* @param cffApprovalDetailsSid the primary key of the cff approval details
	* @return the cff approval details
	* @throws PortalException if a cff approval details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.CffApprovalDetails getCffApprovalDetails(
		int cffApprovalDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cffApprovalDetailsLocalService.getCffApprovalDetails(cffApprovalDetailsSid);
	}

	/**
	* Returns a range of all the cff approval detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffApprovalDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff approval detailses
	* @param end the upper bound of the range of cff approval detailses (not inclusive)
	* @return the range of cff approval detailses
	*/
	@Override
	public java.util.List<com.stpl.app.parttwo.model.CffApprovalDetails> getCffApprovalDetailses(
		int start, int end) {
		return _cffApprovalDetailsLocalService.getCffApprovalDetailses(start,
			end);
	}

	/**
	* Returns the number of cff approval detailses.
	*
	* @return the number of cff approval detailses
	*/
	@Override
	public int getCffApprovalDetailsesCount() {
		return _cffApprovalDetailsLocalService.getCffApprovalDetailsesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _cffApprovalDetailsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _cffApprovalDetailsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cffApprovalDetailsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the cff approval details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param cffApprovalDetails the cff approval details
	* @return the cff approval details that was updated
	*/
	@Override
	public com.stpl.app.parttwo.model.CffApprovalDetails updateCffApprovalDetails(
		com.stpl.app.parttwo.model.CffApprovalDetails cffApprovalDetails) {
		return _cffApprovalDetailsLocalService.updateCffApprovalDetails(cffApprovalDetails);
	}

	@Override
	public CffApprovalDetailsLocalService getWrappedService() {
		return _cffApprovalDetailsLocalService;
	}

	@Override
	public void setWrappedService(
		CffApprovalDetailsLocalService cffApprovalDetailsLocalService) {
		_cffApprovalDetailsLocalService = cffApprovalDetailsLocalService;
	}

	private CffApprovalDetailsLocalService _cffApprovalDetailsLocalService;
}