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
 * Provides a wrapper for {@link CffCustomViewDetailsLocalService}.
 *
 * @author
 * @see CffCustomViewDetailsLocalService
 * @generated
 */
@ProviderType
public class CffCustomViewDetailsLocalServiceWrapper
	implements CffCustomViewDetailsLocalService,
		ServiceWrapper<CffCustomViewDetailsLocalService> {
	public CffCustomViewDetailsLocalServiceWrapper(
		CffCustomViewDetailsLocalService cffCustomViewDetailsLocalService) {
		_cffCustomViewDetailsLocalService = cffCustomViewDetailsLocalService;
	}

	/**
	* Adds the cff custom view details to the database. Also notifies the appropriate model listeners.
	*
	* @param cffCustomViewDetails the cff custom view details
	* @return the cff custom view details that was added
	*/
	@Override
	public com.stpl.app.parttwo.model.CffCustomViewDetails addCffCustomViewDetails(
		com.stpl.app.parttwo.model.CffCustomViewDetails cffCustomViewDetails) {
		return _cffCustomViewDetailsLocalService.addCffCustomViewDetails(cffCustomViewDetails);
	}

	/**
	* Creates a new cff custom view details with the primary key. Does not add the cff custom view details to the database.
	*
	* @param cffCustomViewDetailsSid the primary key for the new cff custom view details
	* @return the new cff custom view details
	*/
	@Override
	public com.stpl.app.parttwo.model.CffCustomViewDetails createCffCustomViewDetails(
		int cffCustomViewDetailsSid) {
		return _cffCustomViewDetailsLocalService.createCffCustomViewDetails(cffCustomViewDetailsSid);
	}

	/**
	* Deletes the cff custom view details from the database. Also notifies the appropriate model listeners.
	*
	* @param cffCustomViewDetails the cff custom view details
	* @return the cff custom view details that was removed
	*/
	@Override
	public com.stpl.app.parttwo.model.CffCustomViewDetails deleteCffCustomViewDetails(
		com.stpl.app.parttwo.model.CffCustomViewDetails cffCustomViewDetails) {
		return _cffCustomViewDetailsLocalService.deleteCffCustomViewDetails(cffCustomViewDetails);
	}

	/**
	* Deletes the cff custom view details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cffCustomViewDetailsSid the primary key of the cff custom view details
	* @return the cff custom view details that was removed
	* @throws PortalException if a cff custom view details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.CffCustomViewDetails deleteCffCustomViewDetails(
		int cffCustomViewDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cffCustomViewDetailsLocalService.deleteCffCustomViewDetails(cffCustomViewDetailsSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cffCustomViewDetailsLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _cffCustomViewDetailsLocalService.dynamicQuery();
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
		return _cffCustomViewDetailsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffCustomViewDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _cffCustomViewDetailsLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffCustomViewDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _cffCustomViewDetailsLocalService.dynamicQuery(dynamicQuery,
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
		return _cffCustomViewDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _cffCustomViewDetailsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.parttwo.model.CffCustomViewDetails fetchCffCustomViewDetails(
		int cffCustomViewDetailsSid) {
		return _cffCustomViewDetailsLocalService.fetchCffCustomViewDetails(cffCustomViewDetailsSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _cffCustomViewDetailsLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the cff custom view details with the primary key.
	*
	* @param cffCustomViewDetailsSid the primary key of the cff custom view details
	* @return the cff custom view details
	* @throws PortalException if a cff custom view details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.CffCustomViewDetails getCffCustomViewDetails(
		int cffCustomViewDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cffCustomViewDetailsLocalService.getCffCustomViewDetails(cffCustomViewDetailsSid);
	}

	/**
	* Returns a range of all the cff custom view detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffCustomViewDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff custom view detailses
	* @param end the upper bound of the range of cff custom view detailses (not inclusive)
	* @return the range of cff custom view detailses
	*/
	@Override
	public java.util.List<com.stpl.app.parttwo.model.CffCustomViewDetails> getCffCustomViewDetailses(
		int start, int end) {
		return _cffCustomViewDetailsLocalService.getCffCustomViewDetailses(start,
			end);
	}

	/**
	* Returns the number of cff custom view detailses.
	*
	* @return the number of cff custom view detailses
	*/
	@Override
	public int getCffCustomViewDetailsesCount() {
		return _cffCustomViewDetailsLocalService.getCffCustomViewDetailsesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _cffCustomViewDetailsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _cffCustomViewDetailsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cffCustomViewDetailsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the cff custom view details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param cffCustomViewDetails the cff custom view details
	* @return the cff custom view details that was updated
	*/
	@Override
	public com.stpl.app.parttwo.model.CffCustomViewDetails updateCffCustomViewDetails(
		com.stpl.app.parttwo.model.CffCustomViewDetails cffCustomViewDetails) {
		return _cffCustomViewDetailsLocalService.updateCffCustomViewDetails(cffCustomViewDetails);
	}

	@Override
	public CffCustomViewDetailsLocalService getWrappedService() {
		return _cffCustomViewDetailsLocalService;
	}

	@Override
	public void setWrappedService(
		CffCustomViewDetailsLocalService cffCustomViewDetailsLocalService) {
		_cffCustomViewDetailsLocalService = cffCustomViewDetailsLocalService;
	}

	private CffCustomViewDetailsLocalService _cffCustomViewDetailsLocalService;
}